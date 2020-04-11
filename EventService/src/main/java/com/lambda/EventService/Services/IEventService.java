package com.lambda.EventService.Services;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Entity.Event;

public interface IEventService {
    Event createEvent(Event info) throws CustomEventException;
    Event findById(Long id) throws CustomEventException;
    Event updateEventStatus(Event updatedEvent)throws CustomEventException;
}
