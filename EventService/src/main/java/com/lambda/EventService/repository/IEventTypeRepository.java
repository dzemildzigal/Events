package com.lambda.EventService.repository;

import com.lambda.EventService.models.EventType;
import org.springframework.data.repository.CrudRepository;

public interface IEventTypeRepository extends CrudRepository<EventType, Long> {
    EventType findById(long id);
}
