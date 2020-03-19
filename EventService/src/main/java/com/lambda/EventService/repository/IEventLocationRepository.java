package com.lambda.EventService.repository;

import com.lambda.EventService.models.Event;
import com.lambda.EventService.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;

public interface IEventLocationRepository extends CrudRepository<Location,Long> {
    Location findById(long id);
}
