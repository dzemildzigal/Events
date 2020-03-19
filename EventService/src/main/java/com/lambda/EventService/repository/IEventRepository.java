package com.lambda.EventService.repository;

import com.lambda.EventService.models.Event;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface IEventRepository extends CrudRepository<Event,Long> {
    Event findById(long id);
}
