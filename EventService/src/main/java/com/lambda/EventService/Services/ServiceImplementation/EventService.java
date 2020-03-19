package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.Services.IEventService;
import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Repositories.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

    @Autowired
    IEventRepository eventRepository;
    @Override
    public Event createEvent(Event info) {
        //test
        var eventRepositoryTemp = eventRepository.save(info);
        return info;
    }

    @Override
    public Event findById(Long id) {
        long idd= id;
        return eventRepository.findById(idd);
    }
}
