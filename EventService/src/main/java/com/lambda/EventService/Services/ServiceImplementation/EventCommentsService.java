package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.Services.IEventCommentsService;
import com.lambda.EventService.models.EventComments;
import com.lambda.EventService.repository.IEventCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EventCommentsService implements IEventCommentsService {

    @Autowired
    IEventCommentsRepository eventCommentsRepository;


    @Override
    public EventComments createEventComments(EventComments object) {
        //test
        var eventCommentsRepositoryTemp = eventCommentsRepository.save(object);
        return object;
    }

    @Override
    public EventComments findById(Long id) {
        long idd = id;
        return eventCommentsRepository.findById(idd);
    }
}
