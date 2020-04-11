package com.lambda.EventService.Repositories;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Entity.EventComments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEventCommentsRepository extends CrudRepository<EventComments,Long> {
    EventComments findById(long id)throws CustomEventException;
    List<EventComments> findByUserId(long id)throws CustomEventException;
    List<EventComments> findByTextContainingIgnoreCase(String containingString)throws CustomEventException;
}
