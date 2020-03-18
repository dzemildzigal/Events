package com.lambda.EventService.Services;

import com.lambda.EventService.models.Event;

public interface IEventService {
    Event createEvent(Event info);
    Event findById(Long id);
}
