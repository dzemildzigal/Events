package com.lambda.EventService.Services;

import com.lambda.EventService.Models.Location;

public interface ILocationService {
    Location createLocation(Location object);
    Location findById(Long id);
}
