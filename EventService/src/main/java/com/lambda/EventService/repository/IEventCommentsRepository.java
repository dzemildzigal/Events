package com.lambda.EventService.repository;

import com.lambda.EventService.models.EventComments;
import org.springframework.data.repository.CrudRepository;

public interface IEventCommentsRepository extends CrudRepository<EventComments,Long> {

}