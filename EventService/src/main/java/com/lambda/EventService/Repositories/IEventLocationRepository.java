package com.lambda.EventService.Repositories;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Entity.Location;
import org.springframework.data.repository.CrudRepository;

public interface IEventLocationRepository extends CrudRepository<Location,Long> {
    Location findById(long id)throws CustomEventException;
}
