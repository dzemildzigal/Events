package com.lambda.EventService.Services;

import com.lambda.EventService.models.Event;

public interface IEventService {
    void createEvent(Event info);
    Event findById(long id);
}
