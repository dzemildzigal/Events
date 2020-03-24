package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.Services.IEventTypeService;
import com.lambda.EventService.Models.EventType;
import com.lambda.EventService.Repositories.IEventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventTypeService implements IEventTypeService {

    @Autowired
    IEventTypeRepository eventTypeRepository;



    @Override
    public EventType createEventType(EventType object) {
        var eventTypeRepositoryTemp = eventTypeRepository.save(object);
        return object;
    }

    @Override
    public EventType findById(Long id) {
        long idd = id;
        return eventTypeRepository.findById(idd);
    }
}
