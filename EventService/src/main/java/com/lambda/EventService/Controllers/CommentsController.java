package com.lambda.EventService.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.Models.EnuEventStatus;
import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Models.EventComments;
import com.lambda.EventService.Services.IEventCommentsService;
import com.lambda.EventService.Services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentsController {
    @Autowired
    IEventCommentsService commentService;
    @Autowired
    IEventService eventService;

    @GetMapping("/{id}")
    public EventComments getEventComment(@PathVariable long id)throws  Exception{
        return commentService.findById(id);
    }

    @GetMapping("/event/{eventId}")
    public List<EventComments> getEventComentsByEventId(@PathVariable long eventId) throws Exception{
        var event = eventService.findById(eventId);
        return event.getEventCommentsList();
    }

    @PostMapping("/post-comment/{eventId}")
    public Event updateEventStatus(@PathVariable long eventId, @org.jetbrains.annotations.NotNull EventComments comment) throws Exception{
        var event = eventService.findById(eventId);
        comment.setEvent(event);
        commentService.createEventComments(comment);
        return eventService.findById(eventId);
    }

}
