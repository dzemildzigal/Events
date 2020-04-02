package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Services.ILocationService;
import com.lambda.EventService.Models.Location;
import com.lambda.EventService.Repositories.IEventLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService implements ILocationService {

    @Autowired
    IEventLocationRepository eventLocationRepository;


    @Override
    public Location createLocation(Location object) throws CustomEventException{
        //test
        var eventLocationRepositoryTemp = eventLocationRepository.save(object);
        return object;
    }

    @Override
    public Location findById(Long id)throws CustomEventException {
        long idd=id;
        return eventLocationRepository.findById(idd);
    }

    @Override
    public Location updateLocation(Location updateVal)throws CustomEventException{
        return eventLocationRepository.save(updateVal);
    }

}
