package com.lambda.EventService.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.Models.EnuEventStatus;
import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Models.EventComments;
import com.lambda.EventService.Services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("event")
public class EventController {
    @Autowired
    IEventService eventService;

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable long id){
        return eventService.findById(id);
    }

    @GetMapping("/status/{id}")
    public EnuEventStatus getEventStatus(@PathVariable long id)throws Exception{
        var event = eventService.findById(id);
        return event.getEnuEventStatus();
    }

    @PostMapping("/update-status")
    public Event updateEventStatus(@PathVariable long id, EnuEventStatus status) throws Exception{
        var event = eventService.findById(id);
        event.setEnuEventStatus(status);
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

    @PostMapping("/update-event")
    public Event updateEventStatus(@RequestBody Event event, EnuEventStatus newStatus) throws Exception{
        event.setEnuEventStatus(newStatus);
        return eventService.updateEventStatus(event);
    }
}
