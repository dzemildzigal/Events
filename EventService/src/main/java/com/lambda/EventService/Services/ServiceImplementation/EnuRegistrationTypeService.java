package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.Services.IEnuRegistrationTypeService;
import com.lambda.EventService.Models.EnuRegistrationType;
import com.lambda.EventService.Repositories.IEnuRegistrationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnuRegistrationTypeService implements IEnuRegistrationTypeService {

    @Autowired
    IEnuRegistrationTypeRepository enuRegistrationTypeRepository;

    @Override
    public EnuRegistrationType createEnuRegistrationType(EnuRegistrationType object) {
        //test
        var enuRegistrationTypeTemp = enuRegistrationTypeRepository.save(object);
        return object;
    }

    @Override
    public EnuRegistrationType findById(Long id) {
        long idd = id;
        return enuRegistrationTypeRepository.findById(idd);
    }
}
