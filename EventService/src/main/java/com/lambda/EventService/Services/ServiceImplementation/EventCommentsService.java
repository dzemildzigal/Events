package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Services.IEventCommentsService;
import com.lambda.EventService.Models.EventComments;
import com.lambda.EventService.Repositories.IEventCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCommentsService implements IEventCommentsService {

    @Autowired
    IEventCommentsRepository eventCommentsRepository;


    @Override
    public EventComments createEventComments(EventComments object)throws CustomEventException {
        if(object == null || object.getEvent() == null || object.getText() == null)
            throw new CustomEventException("400: One or more attributes of EventComments (possibly whole object) is null!");
        var eventCommentsRepositoryTemp = eventCommentsRepository.save(object);
        return object;
    }

    @Override
    public EventComments findById(Long id) throws CustomEventException{
        long idd = id;
        var result = eventCommentsRepository.findById(idd);
        if(result != null) return result;
        throw new CustomEventException("404: EventComment with ID="+id.toString()+" does not exist in the database!");
    }
    @Override
    public List<EventComments> findByUserId(Long userId)throws CustomEventException{
        long uid = userId;
        var result = eventCommentsRepository.findByUserId(uid);
        if(result != null) return result;
        throw new CustomEventException("404: Comments associated with the User with ID="+userId.toString()+" do not exist in the database!");
    }

    @Override
    public List<EventComments> findByTextContainingIgnoreCase(String containingString)throws CustomEventException{
        String search = containingString;
        return eventCommentsRepository.findByTextContainingIgnoreCase(search);
    }

}
