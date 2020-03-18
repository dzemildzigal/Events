package com.lambda.EventService.Services;

import com.lambda.EventService.models.EventComments;

public interface IEventCommentsService {
    void createEventComments(EventComments object);
    EventComments findById(long id);
}
