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
        var eventCommentsRepositoryTemp = eventCommentsRepository.save(object);
        return object;
    }

    @Override
    public EventComments findById(Long id) throws CustomEventException{
        long idd = id;
        return eventCommentsRepository.findById(idd);
    }
    @Override
    public List<EventComments> findByUserId(Long userId)throws CustomEventException{
        long uid = userId;
        return eventCommentsRepository.findByUserId(uid);
    }

    @Override
    public List<EventComments> findByTextContainingIgnoreCase(String containingString)throws CustomEventException{
        String search = containingString;
        return eventCommentsRepository.findByTextContainingIgnoreCase(search);
    }

}
