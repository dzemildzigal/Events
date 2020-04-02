package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Services.IEnuRegistrationTypeService;
import com.lambda.EventService.Models.EnuRegistrationType;
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
        var enuRegistrationTypeTemp = enuRegistrationTypeRepository.save(object);
        return object;
    }

    @Override
    public EnuRegistrationType findById(Long id)throws CustomEventException {
        long idd = id;
        return enuRegistrationTypeRepository.findById(idd);
    }

    @Override
    public List<EnuRegistrationType> findByDescription(String desc)throws CustomEventException{
        String description = desc;
        return enuRegistrationTypeRepository.findByDescription(description);
    }

}
