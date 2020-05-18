package com.lambda.EventService.Services;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Api.EventFilterDTO;
import com.lambda.EventService.Models.Entity.Event;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IEventService {
    Event createEvent(Event info) throws CustomEventException;
    Event findById(Long id) throws CustomEventException;
    List<Event> findAll(EventFilterDTO eventFilterDTO) throws CustomEventException;
    Event updateEventStatus(Event updatedEvent)throws CustomEventException;
    //List<Event> findByCriteria(EventFilterDTO filter) throws CustomEventException;

}
