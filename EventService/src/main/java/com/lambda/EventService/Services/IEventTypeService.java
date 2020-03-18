package com.lambda.EventService.Services;

import com.lambda.EventService.models.EventType;

public interface IEventTypeService {
    EventType createEventType(EventType object);
    EventType findById(Long id);
}
