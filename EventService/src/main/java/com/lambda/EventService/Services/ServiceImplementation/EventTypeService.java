package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.Services.IEventTypeService;
import com.lambda.EventService.models.EventType;
import com.lambda.EventService.repository.IEventRepository;
import com.lambda.EventService.repository.IEventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EventTypeService implements IEventTypeService {

    @Autowired
    IEventTypeRepository eventTypeRepository;



    @Override
    public EventType createEventType(EventType object) {
        //test
        var eventTypeRepositoryTemp = eventTypeRepository.save(object);
        return object;
    }

    @Override
    public EventType findById(Long id) {
        long idd = id;
        return eventTypeRepository.findById(idd);
    }
}
