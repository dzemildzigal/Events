package com.lambda.EventService.Controllers;


import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Models.EventComments;
import com.lambda.EventService.Services.IEventCommentsService;
import com.lambda.EventService.Services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/user/{userId}")
    public List<EventComments> getEventCommentByUserId(@PathVariable long userId) throws Exception{
        var rez = commentService.findByUserId(userId);
        return rez;
    }

    @GetMapping("/containing/{string}")
    public List<EventComments> getEventCommentsByContainingStringInComment(@PathVariable String string) throws Exception{
        var rez = commentService.findByTextContainingIgnoreCase(string);
        return rez;
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
