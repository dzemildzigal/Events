package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
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
    public EventType createEventType(EventType object)throws CustomEventException {
        if(object == null || object.getEventTypeDescription()== null) throw new CustomEventException("400: One or more attributes of class EventType (possibly whole object) is null!");
        var eventTypeRepositoryTemp = eventTypeRepository.save(object);
        return object;
    }

    @Override
    public EventType findById(Long id) throws CustomEventException {
        long idd = id;
        var result = eventTypeRepository.findById(idd);
        if(result != null) return result;
        throw new CustomEventException("404: The EventType with ID="+id.toString()+" does not exist in the database.");
    }

    @Override
    public EventType updateEventType(EventType updatedValue)throws CustomEventException{
        if(updatedValue == null || updatedValue.getEventTypeDescription()== null || updatedValue.getEventTypeId() == null || updatedValue.getEventList().size() == 0)
            throw new CustomEventException("400: One or more attributes of class EventType (possibly whole object) is null or EventList is empty!");
        return eventTypeRepository.save(updatedValue);
    }
}
