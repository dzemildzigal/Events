package com.lambda.EventService.Services;

import com.lambda.EventService.Models.EventComments;

public interface IEventCommentsService {
    EventComments createEventComments(EventComments object);
    EventComments findById(Long id);
}
