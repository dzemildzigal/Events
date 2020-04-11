package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Services.IEnuRegistrationTypeService;
import com.lambda.EventService.Models.Entity.EnuRegistrationType;
import com.lambda.EventService.Repositories.IEnuRegistrationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnuRegistrationTypeService implements IEnuRegistrationTypeService {

    @Autowired
    IEnuRegistrationTypeRepository enuRegistrationTypeRepository;

    @Override
    public EnuRegistrationType createEnuRegistrationType(EnuRegistrationType object) throws CustomEventException {
        //test
        if(object==null || object.getDescription() == null) throw new CustomEventException("400: One or more attributes of EnuRegistrationType (possibly whole object) is null");
        var enuRegistrationTypeTemp = enuRegistrationTypeRepository.save(object);
        return object;
    }

    @Override
    public EnuRegistrationType findById(Long id)throws CustomEventException {
        long idd = id;
        var result = enuRegistrationTypeRepository.findById(idd);
        if(result != null) return result;
        throw new CustomEventException("404: EnuRegistrationType with ID="+id.toString()+" does not exist in the database!");
    }

    @Override
    public List<EnuRegistrationType> findByDescription(String desc)throws CustomEventException{
        String description = desc;
        var result = enuRegistrationTypeRepository.findByDescription(description);
        if(result != null) return result;
        throw new CustomEventException("404: List of EnuRegistrationType's associated with the Description=\""+desc+"\" does not exist in the database!");
    }

}
