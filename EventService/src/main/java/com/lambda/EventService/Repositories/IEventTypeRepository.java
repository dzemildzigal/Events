package com.lambda.EventService.Repositories;

import com.lambda.EventService.Models.EventType;
import org.springframework.data.repository.CrudRepository;

public interface IEventTypeRepository extends CrudRepository<EventType, Long> {
    EventType findById(long id);
}
