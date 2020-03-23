package com.lambda.EventService.Repositories;

import com.lambda.EventService.Models.Location;
import org.springframework.data.repository.CrudRepository;

public interface IEventLocationRepository extends CrudRepository<Location,Long> {
    Location findById(long id);
}
