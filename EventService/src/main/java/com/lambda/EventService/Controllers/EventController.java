package com.lambda.EventService.Controllers;

import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("event")
public class EventController {
    @Autowired
    IEventService eventService;

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable long id){
        var x = eventService.findById(id);
        return x;
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
