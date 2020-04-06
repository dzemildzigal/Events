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
        if(object.getDescription() == null || object == null) throw new CustomEventException("400: One or more parameters (or whole object) Location is null!");
        var eventLocationRepositoryTemp = eventLocationRepository.save(object);
        return object;
    }

    @Override
    public Location findById(Long id)throws CustomEventException {
        long idd=id;
        var result = eventLocationRepository.findById(idd);
        if(result != null) return result;
        else throw new CustomEventException("404: Location with ID="+id.toString()+" does not exist in the database!");
    }

    @Override
    public Location updateLocation(Location updateVal)throws CustomEventException{
        if(updateVal.getDescription() == null || updateVal == null)
            throw new CustomEventException("400: One or more parameters (or whole object) Location is null!");
        return eventLocationRepository.save(updateVal);
    }

}
