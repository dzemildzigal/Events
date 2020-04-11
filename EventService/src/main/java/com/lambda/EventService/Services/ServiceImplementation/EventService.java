package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Services.IEventService;
import com.lambda.EventService.Models.Entity.Event;
import com.lambda.EventService.Repositories.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

    @Autowired
    IEventRepository eventRepository;
    @Override
    public Event createEvent(Event info) throws CustomEventException {
        if(info.getLocation() == null || info.getEventType()==null || info.getCanBuyTicket() == null || info == null)
            throw new CustomEventException("400: Event is null or one or more attributes of class Event are null!");
        var eventRepositoryTemp = eventRepository.save(info);
        return info;
    }

    @Override
    public Event findById(Long id) throws CustomEventException {
        long idd= id;
        var result = eventRepository.findById(idd);
        if(result != null) return result;
        throw new CustomEventException("404: Event with ID="+id.toString()+" not found in the database!");
    }

    @Override
    public Event updateEventStatus(Event info)throws CustomEventException{
        if(info.getEventId() == null || info.getLocation() == null || info.getEventType()==null || info.getCanBuyTicket() == null || info == null)
        throw new CustomEventException("400: Event is null or one or more attributes of class Event are null!");
        return eventRepository.save(info);
    }

}
