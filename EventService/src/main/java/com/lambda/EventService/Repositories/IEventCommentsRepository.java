package com.lambda.EventService.Repositories;

import com.lambda.EventService.Models.EventComments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEventCommentsRepository extends CrudRepository<EventComments,Long> {
    EventComments findById(long id);
    List<EventComments> findByUserId(long id);
    List<EventComments> findByTextContainingIgnoreCase(String containingString);
}
