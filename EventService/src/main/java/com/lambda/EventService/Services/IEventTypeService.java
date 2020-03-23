package com.lambda.EventService.Services;

import com.lambda.EventService.Models.EventType;

public interface IEventTypeService {
    EventType createEventType(EventType object);
    EventType findById(Long id);
}
