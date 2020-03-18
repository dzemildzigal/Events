package com.lambda.EventService.repository;

import com.lambda.EventService.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface IEventRepository extends CrudRepository<Event,Long> {

}
