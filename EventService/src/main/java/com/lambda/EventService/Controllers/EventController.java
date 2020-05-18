package com.lambda.EventService.Controllers;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Helpers.NotificationServiceHelper;
import com.lambda.EventService.Helpers.UserServiceHelper;
import com.lambda.EventService.Models.Api.BuyATicketDTO;
import com.lambda.EventService.Models.Api.EnuEventStatusWrapperDTO;
import com.lambda.EventService.Models.Api.EventFilterDTO;
import com.lambda.EventService.Models.Api.EventWrapperDTO;
import com.lambda.EventService.Models.Entity.EnuEventStatus;
import com.lambda.EventService.Models.Entity.Event;
import com.lambda.EventService.Models.Entity.EventType;
import com.lambda.EventService.Models.Entity.Location;
import com.lambda.EventService.Services.IEnuEventStatusService;
import com.lambda.EventService.Services.IEventService;
import com.lambda.EventService.Services.IEventTypeService;
import com.lambda.EventService.Services.ILocationService;
import com.lambda.EventService.Services.ServiceImplementation.ProducerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    ProducerService producerService;

    @PostMapping(path="/search",produces={MediaType.APPLICATION_JSON_VALUE})
    public List<Event> getFilteredEvents(@RequestBody EventFilterDTO filter) throws CustomEventException{
        List<Event> output = eventService.findAll(filter);
        return output;
    }

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
    public Event updateEvent(@RequestParam Long oldEventId, @RequestBody EventWrapperDTO eventWrapper, @RequestHeader(value = "Authorization") String authorizationToken) throws Exception{
        if(eventWrapper.getEnuEventStatus() == null || eventWrapper.getEvent()== null || eventWrapper.getEventType() == null || eventWrapper.getLocation() == null)
            throw new CustomEventException("400: Bad request. One or more properties of the class EventWrapperDTO are null!");
        if(eventWrapper.getUserId() == null)
            throw new CustomEventException("400: Bad request. userId property of EventWrapperDTO (for authorization) is null!");

        var userId = eventWrapper.getUserId();
        var newEvent = eventWrapper.getEvent();
        var newEventLocation = eventWrapper.getLocation();
        var neweventStatus = eventWrapper.getEnuEventStatus();
        var neweventType = eventWrapper.getEventType();
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
            return eventService.updateEventStatus(oldEvent);
        }
        throw new CustomEventException("403: User with ID="+userId.toString()+" is unauthorized to edit the Event with ID="+oldEventId.toString());
    }


    //update the value of the Status of an Event by its ID and corresponding new Status
    @PutMapping(path = "/update-status/{eventId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Event updateEventStatus(@PathVariable long eventId, @RequestBody EnuEventStatusWrapperDTO enuEventStatusWrapper, @RequestHeader(value = "Authorization") String authorizationToken) throws Exception{
        if(enuEventStatusWrapper.getEnuEventStatus() == null)
            throw new CustomEventException("400: Bad request. Property enuEventStatus of EnuEventStatusWrapperDTO class is null!!");
        if(enuEventStatusWrapper.getUserId() == null)
            throw new CustomEventException("400: Bad request. userId property of EnuEventStatusWrapperDTO (for authorization) is null!");

        var userId = enuEventStatusWrapper.getUserId();
        var status = enuEventStatusWrapper.getEnuEventStatus();
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
            else {
                var event = eventService.findById(eventId);
                status.getEvents().add(event);
                event.setEnuEventStatus(enuEventStatusService.updateEnuEventStatus(status));
                return event;
            }
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
    public Event addEvent(@RequestBody EventWrapperDTO eventW, @RequestHeader(value = "Authorization") String authorizationToken)throws Exception{

        if(eventW.getEnuEventStatus() == null || eventW.getEvent()== null || eventW.getEventType() == null || eventW.getLocation() == null)
            throw new CustomEventException("400: Bad request. One or more properties of the class EventWrapperDTO are null!");
        if(eventW.getUserId() == null)
            throw new CustomEventException("400: Bad request. userId property of EventWrapperDTO (for authorization) is null!");
        var event = eventW.event;
        var userId = event.getCreatedByUserId();
        event.setEnuEventStatus(eventW.enuEventStatus);
        event.setEventType(eventW.eventType);
        event.setLocation(eventW.location);
        if(userServiceHelper.CheckUserAuthorised(userId.toString(),authorizationToken)){
            try {
                locationService.findById(event.getLocation().getLocationId());
            }catch(Exception ex){
                locationService.createLocation(event.getLocation());
            }
            try {
                enuEventStatusService.findById(event.getEnuEventStatus().getEventStatusId());

            }catch(Exception ex){
                enuEventStatusService.createEnuEventStatus(event.getEnuEventStatus());
            }
            try {
                eventTypeService.findById(event.getEventType().getEventTypeId());

            }catch(Exception ex){
                eventTypeService.createEventType(event.getEventType());
            }
            //var x = locationService.updateLocation(event.getLocation());
            //var y = enuEventStatusService.updateEnuEventStatus(event.getEnuEventStatus());
            //var z = eventTypeService.updateEventType(event.getEventType());
            //event.setLocation(x);
            //event.setEnuEventStatus(y);
            //event.setEventType(z);
            event.setEventType(eventTypeService.findByEventTypeDescription(event.getEventType().getEventTypeDescription()).get(0));
            producerService.send(event);
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
