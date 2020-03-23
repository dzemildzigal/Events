package com.lambda.EventService.Repositories;

import com.lambda.EventService.Models.Event;
import org.springframework.data.repository.CrudRepository;

public interface IEventRepository extends CrudRepository<Event,Long> {
    Event findById(long id);
}
