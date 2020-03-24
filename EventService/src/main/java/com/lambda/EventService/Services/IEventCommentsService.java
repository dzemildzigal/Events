package com.lambda.EventService.Services;

import com.lambda.EventService.Models.EventComments;

import java.util.List;

public interface IEventCommentsService {
    EventComments createEventComments(EventComments object);
    EventComments findById(Long id);
    List<EventComments> findByUserId(Long userId);
    List<EventComments> findByTextContainingIgnoreCase(String containingString);
}
