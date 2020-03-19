package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.Services.IEventCommentsService;
import com.lambda.EventService.Models.EventComments;
import com.lambda.EventService.Repositories.IEventCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
