package com.lambda.EventService.Controllers;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Helpers.NotificationServiceHelper;
import com.lambda.EventService.Helpers.UserServiceHelper;
import com.lambda.EventService.Models.Api.BuyATicketDTO;
import com.lambda.EventService.Models.EnuEventStatus;
import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Models.EventType;
import com.lambda.EventService.Models.Location;
import com.lambda.EventService.Services.IEnuEventStatusService;
import com.lambda.EventService.Services.IEventService;
import com.lambda.EventService.Services.IEventTypeService;
import com.lambda.EventService.Services.ILocationService;
import com.netflix.discovery.converters.Auto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("event")
public class EventController {
    @Autowired
    IEventService eventService;
    @Autowired
    IEnuEventStatusService enuEventStatusService;
    @Autowired
    ILocationService locationService;
    @Autowired
    IEventTypeService eventTypeService;
    @Autowired
    UserServiceHelper userServiceHelper;
    @Autowired
    NotificationServiceHelper notificationServiceHelper;
    //Get the Event by its ID
    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event getEvent(@PathVariable long id)throws CustomEventException {
        return eventService.findById(id);
    }

    //Get the Event location by the Event ID
    @GetMapping(path = "/location/{eventId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Location getEventLocation(@PathVariable long eventId) throws CustomEventException{
        Long eid = eventId;
        var event = eventService.findById(eventId);
        var locId = event.getLocation().getLocationId();
        return locationService.findById(locId);
    }

    //Get the Status of an Event by its ID
    @GetMapping(path = "/status/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public EnuEventStatus getEventStatusByEventId(@PathVariable long id) throws CustomEventException{
        var event = eventService.findById(id);
        return event.getEnuEventStatus();
    }

    //Get the type of the Event by the Event ID
    @GetMapping(path = "/type/{eventId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public EventType getEventTypeByEventId(@PathVariable long eventId) throws CustomEventException{
        var event = eventService.findById(eventId);
        var typeId = event.getEventType().getEventTypeId();
        return eventTypeService.findById(typeId);

    }

    //Update the whole oldEvent by the newEvent parameters
    @PutMapping(path = "/update-event",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event updateEvent(@RequestParam Long oldEventId,@RequestBody Event newEvent,@RequestBody EnuEventStatus neweventStatus,@RequestBody Location newEventLocation,@RequestBody EventType neweventType,@RequestBody @NotNull Long userId, @RequestHeader(value = "Authorization") String authorizationToken) throws Exception{
        if(userServiceHelper.CheckUserAuthorised(userId.toString(),authorizationToken)){
            var oldEvent = eventService.findById(oldEventId);
            if(!oldEvent.getCreatedByUserId().equals(userId)) throw new CustomEventException("403: User with ID="+userId.toString()+" is unauthorized to edit the Event with ID="+oldEvent.getEventId().toString()+" because it's been created by the User with ID="+oldEvent.getCreatedByUserId().toString());
            if(!oldEvent.getCreatedByUserId().equals(newEvent.getCreatedByUserId())) throw new CustomEventException("403: You are forbidden to change the ID of the User that created the Event!");

                var x = locationService.updateLocation(newEventLocation);
                var y = enuEventStatusService.updateEnuEventStatus(neweventStatus);
                var z = eventTypeService.updateEventType(neweventType);
                newEvent.setLocation(x);
                newEvent.setEnuEventStatus(y);
                newEvent.setEventType(z);

                oldEvent = newEvent;
                oldEvent.setEventId(oldEventId);
                var q = eventService.updateEventStatus(oldEvent);
                return q;
        }
        throw new CustomEventException("403: User with ID="+userId.toString()+" is unauthorized to edit the Event with ID="+oldEventId.toString());
    }


    //update the value of the Status of an Event by its ID and corresponding new Status
    @PutMapping(path = "/update-status/{eventId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event updateEventStatus(@PathVariable long eventId,@RequestBody EnuEventStatus status,@RequestBody @NotNull Long userId, @RequestHeader(value = "Authorization") String authorizationToken) throws Exception{
        if(userServiceHelper.CheckUserAuthorised(userId.toString(),authorizationToken)){
            if(status!=null && status.getDescription() != null && status.getEventStatusId()!= null) {
                var event = eventService.findById(eventId);
                    if(!event.getCreatedByUserId().equals(userId)) throw  new CustomEventException("403: User with ID="+userId.toString()+" can not update the status of an Event that they didn't create!");
                var oldStatus = event.getEnuEventStatus();

                oldStatus.getEvents().remove(event);
                var newStatus = enuEventStatusService.findByDescription(status.getDescription());
                if (newStatus == null) {
                    if (status.getEvents() == null)
                        status.setEvents(new ArrayList<Event>());
                    enuEventStatusService.createEnuEventStatus(status);
                    newStatus = enuEventStatusService.findByDescription(status.getDescription());
                }
                newStatus.getEvents().add(event);
                event.setEnuEventStatus(newStatus);
                enuEventStatusService.updateEnuEventStatus(newStatus);
                eventService.updateEventStatus(event);
                return event;
            }
            else
                enuEventStatusService.updateEnuEventStatus(status);
        }
        throw new CustomEventException("500: Unexpected outcome of updateEventStatus() method.");
    }


    @PutMapping(path = "/buy-ticket",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event buyTicket(@RequestBody BuyATicketDTO buyATicketDTO, @RequestHeader(value = "Authorization") String authorizationToken) throws Exception{
        if(userServiceHelper.CheckUserAuthorised(buyATicketDTO.getUserId().toString(),authorizationToken)){
           var event = eventService.findById(buyATicketDTO.getEventId());
           if(event.getNumberOfTicketsAvailable()-buyATicketDTO.getNumberOfTickets()<0)
               throw new CustomEventException("400: Bad request by the client. The needed number of tickets "+buyATicketDTO.getNumberOfTickets().toString()+" can not be bought. This event has only "+event.getNumberOfTicketsAvailable().toString()+" tickets available to sell." );
           event.setNumberOfTicketsAvailable(event.getNumberOfTicketsAvailable()-buyATicketDTO.getNumberOfTickets());
           return eventService.updateEventStatus(event);
        }
        throw new CustomEventException("403: User with ID="+buyATicketDTO.getUserId().toString()+" is unauthorized to edit the Event with ID="+buyATicketDTO.getEventId().toString());
    }

    //Add a new Event by using corresponding new Event data, x-www-urlencoded => @RequestBody
    @PostMapping(path = "/add-event",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event addEvent(@RequestBody Event event,@RequestBody EnuEventStatus eventStatus,@RequestBody Location eventLocation,@RequestBody EventType eventType,@RequestBody @NotNull Long userId, @RequestHeader(value = "Authorization") String authorizationToken)throws Exception{
        if(userServiceHelper.CheckUserAuthorised(userId.toString(),authorizationToken)){
            var x = locationService.updateLocation(eventLocation);
            var y = enuEventStatusService.updateEnuEventStatus(eventStatus);
            var z = eventTypeService.updateEventType(eventType);
            event.setLocation(x);
            event.setEnuEventStatus(y);
            event.setEventType(z);
            if(!event.getCreatedByUserId().equals(userId)) throw new CustomEventException("403: New Event does not have the attribute createdByUserId set to the value of the authenticated User!");
            notificationServiceHelper.notifyUsersOfEventCreation(event);
            return eventService.createEvent(event);
        }
        throw new CustomEventException("500: Unexpected outcome of addEvent() method.");
    }

    //Delete an Existing event by its ID, form-data => @RequestParam
    @DeleteMapping(path = "/delete-event",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event deleteEvent(@RequestParam long eventId,@RequestBody @NotNull Long userId, @RequestHeader(value = "Authorization") String authorizationToken) throws Exception{
        if(userServiceHelper.CheckUserAuthorised(userId.toString(),authorizationToken)){

            var event = eventService.findById(eventId);
            if(!event.getCreatedByUserId().equals(userId)) throw new CustomEventException("403: User with ID="+userId+" can not delete the Event created by User with ID="+event.getCreatedByUserId().toString());

            var oldStatus = event.getEnuEventStatus();

            oldStatus.getEvents().remove(event);
            var newStatus = enuEventStatusService.findByDescription("Obrisan");
            if (newStatus == null){
                newStatus = new EnuEventStatus(null,"Obrisan",new ArrayList<Event>());
                newStatus.getEvents().add(event);
                if(oldStatus.getEvents()==null)
                    oldStatus.setEvents(new ArrayList<Event>());
                enuEventStatusService.createEnuEventStatus(newStatus);
                newStatus = enuEventStatusService.findByDescription(oldStatus.getDescription());
            }
            event.setEnuEventStatus(newStatus);
            eventService.updateEventStatus(event);

            return event;
        }
        throw new CustomEventException("500: Unexpected outcome of deleteEvent() method.");
    }

}
