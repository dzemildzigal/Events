package com.lambda.EventService.Services;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Location;

public interface ILocationService {
    Location createLocation(Location object)throws CustomEventException;
    Location updateLocation(Location object)throws CustomEventException;
    Location findById(Long id)throws CustomEventException;
}
