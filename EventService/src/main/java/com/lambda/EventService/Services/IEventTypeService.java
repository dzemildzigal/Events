package com.lambda.EventService.Services;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Entity.EventType;

import java.util.List;

public interface IEventTypeService {
    EventType createEventType(EventType object)throws CustomEventException;
    EventType findById(Long id)throws CustomEventException;
    List<EventType> findByEventTypeDescription(String desc) throws CustomEventException;
    EventType updateEventType(EventType updatedValue)throws CustomEventException;
}
