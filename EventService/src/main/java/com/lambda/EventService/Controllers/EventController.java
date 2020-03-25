package com.lambda.EventService.Controllers;

import com.lambda.EventService.Models.EnuEventStatus;
import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Models.EventType;
import com.lambda.EventService.Models.Location;
import com.lambda.EventService.Services.IEnuEventStatusService;
import com.lambda.EventService.Services.IEventService;
import com.lambda.EventService.Services.IEventTypeService;
import com.lambda.EventService.Services.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Get the Event by its ID
    @GetMapping("/{id}")
    public Event getEvent(@PathVariable long id){
        return eventService.findById(id);
    }

    //Get the Event location by the Event ID
    @GetMapping("/location/{eventId}")
    public Location getEventLocation(@PathVariable long eventId) throws Exception{
        var event = eventService.findById(eventId);
        var locId = event.getLocation().getLocationId();
        return locationService.findById(locId);
    }

    //Get the Status of an Event by its ID
    @GetMapping("/status/{id}")
    public EnuEventStatus getEventStatusByEventId(@PathVariable long id) throws Exception{
        var event = eventService.findById(id);
        return event.getEnuEventStatus();
    }

    //Get the type of the Event by the Event ID
    @GetMapping("/type/{eventId}")
    public EventType getEventTypeByEventId(@PathVariable long eventId) throws Exception{
        var event = eventService.findById(eventId);
        var typeId = event.getEventType().getEventTypeId();
        return eventTypeService.findById(typeId);

    }

    //Update the whole oldEvent by the newEvent parameters
    @PostMapping("/update-event")
    public Event updateEvent(@RequestParam Long oldEventId,Event newEvent, EnuEventStatus neweventStatus, Location neweventLocation, EventType neweventType) throws Exception{
        var x = locationService.updateLocation(neweventLocation);
        var y = enuEventStatusService.updateEnuEventStatus(neweventStatus);
        var z = eventTypeService.updateEventType(neweventType);
        newEvent.setLocation(x);
        newEvent.setEnuEventStatus(y);
        newEvent.setEventType(z);
        var oldEvent = eventService.findById(oldEventId);
        oldEvent = newEvent;
        oldEvent.setEventId(oldEventId);
        var q = eventService.updateEventStatus(oldEvent);
        return q;
    }


    //update the value of the Status of an Event by its ID and corresponding new Status
    @PostMapping("/update-status/{eventId}")
    public Event updateEventStatus(@PathVariable long eventId, EnuEventStatus status) throws Exception{
        var event = eventService.findById(eventId);
        var oldStatus = event.getEnuEventStatus();

        oldStatus.getEvents().remove(event);
        var newStatus = enuEventStatusService.findByDescription(status.getDescription());
        if (newStatus == null){
                if(status.getEvents()==null)
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

    //Add a new Event by using corresponding new Event data, x-www-urlencoded => @RequestBody
    @PostMapping("/add-event")
    public Event addEvent(Event event, EnuEventStatus eventStatus, Location eventLocation, EventType eventType)throws Exception{
        var x = locationService.updateLocation(eventLocation);
        var y = enuEventStatusService.updateEnuEventStatus(eventStatus);
        var z = eventTypeService.updateEventType(eventType);
        event.setLocation(x);
        event.setEnuEventStatus(y);
        event.setEventType(z);
        var q = eventService.createEvent(event);
        return q;
    }

    //Delete an Existing event by its ID, form-data => @RequestParam
    @PostMapping("/delete-event")
    public Event deleteEvent(@RequestParam long eventId) throws Exception{
        var event = eventService.findById(eventId);
        var oldStatus = event.getEnuEventStatus();

        oldStatus.getEvents().remove(event);
        var newStatus = enuEventStatusService.findByDescription("Obrisan");
        if (newStatus == null){
            if(oldStatus.getEvents()==null)
                oldStatus.setEvents(new ArrayList<Event>());
            oldStatus.setDescription("Obrisan");
            enuEventStatusService.createEnuEventStatus(oldStatus);
            newStatus = enuEventStatusService.findByDescription(oldStatus.getDescription());
        }
        newStatus.getEvents().add(event);
        event.setEnuEventStatus(newStatus);
        enuEventStatusService.updateEnuEventStatus(newStatus);
        eventService.updateEventStatus(event);

        return event;
    }

}
