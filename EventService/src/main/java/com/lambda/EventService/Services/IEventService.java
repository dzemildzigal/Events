package com.lambda.EventService.Services;

import com.lambda.EventService.models.Event;
import org.springframework.stereotype.Service;

public interface IEventService {
    Event createEvent(Event info);
    Event findById(Long id);
}
