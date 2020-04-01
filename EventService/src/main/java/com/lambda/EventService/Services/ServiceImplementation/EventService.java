package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Services.IEventService;
import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Repositories.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class EventService implements IEventService {

    @Autowired
    IEventRepository eventRepository;
    @Override
    public Event createEvent(Event info) throws CustomEventException {
        //test
        var eventRepositoryTemp = eventRepository.save(info);
        return info;
    }

    @Override
    public Event findById(Long id) throws CustomEventException {
        long idd= id;
        var result = eventRepository.findById(idd);
        if(result != null) return result;
        throw new CustomEventException("404 : Event with ID="+id.toString()+" not found");
    }

    @Override
    public Event updateEventStatus(Event info)throws CustomEventException{
        return eventRepository.save(info);
    }

}
