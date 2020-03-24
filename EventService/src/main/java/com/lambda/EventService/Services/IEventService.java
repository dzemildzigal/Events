package com.lambda.EventService.Services;

import com.lambda.EventService.Models.Event;

public interface IEventService {
    Event createEvent(Event info);
    Event findById(Long id);

    Event updateEventStatus(Event updatedEvent)throws Exception;
}
