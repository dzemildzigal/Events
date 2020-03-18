package com.lambda.EventService.Services;

import com.lambda.EventService.models.EventType;

public interface IEventTypeService {
    void createEventType(EventType object);
    EventType findById(long id);
}
