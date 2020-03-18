package com.lambda.EventService.Services;

import com.lambda.EventService.models.Location;

public interface ILocationService {
    void createLocation(Location object);
    Location findById(long id);
}
