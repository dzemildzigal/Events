package com.lambda.EventService.Repositories;

import com.lambda.EventService.Models.EventComments;
import org.springframework.data.repository.CrudRepository;

public interface IEventCommentsRepository extends CrudRepository<EventComments,Long> {
    EventComments findById(long id);

}
