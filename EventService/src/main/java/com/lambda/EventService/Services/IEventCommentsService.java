package com.lambda.EventService.Services;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Entity.EventComments;

import java.util.List;

public interface IEventCommentsService {
    EventComments createEventComments(EventComments object)throws CustomEventException;
    EventComments findById(Long id)throws CustomEventException;
    List<EventComments> findByUserId(Long userId)throws CustomEventException;
    List<EventComments> findByTextContainingIgnoreCase(String containingString)throws CustomEventException;
}
