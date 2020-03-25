package com.lambda.EventService.Controllers;

import com.lambda.EventService.Models.EnuEventStatus;
import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Services.IEnuEventStatusService;
import com.lambda.EventService.Services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable long id){
        return eventService.findById(id);
    }

    @GetMapping("/status/{id}")
    public EnuEventStatus getEventStatusByEventId(@PathVariable long id)throws Exception{
        var event = eventService.findById(id);
        return event.getEnuEventStatus();
    }

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

    @PostMapping("/add-event")
    public Event addEvent(@RequestBody Event event)throws Exception{
        return eventService.createEvent(event);
    }

    @PostMapping("/delete-event")
    public Event deleteEvent(@RequestBody Event event) throws Exception{
        event.getEnuEventStatus().setDescription("Deleted");
        return eventService.updateEventStatus(event);
    }

}
