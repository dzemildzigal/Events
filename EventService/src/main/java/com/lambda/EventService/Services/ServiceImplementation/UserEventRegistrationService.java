package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.Services.IUserEventRegistrationService;
import com.lambda.EventService.models.UserEventRegistration;
import com.lambda.EventService.repository.IUserEventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEventRegistrationService implements IUserEventRegistrationService {

    @Autowired
    IUserEventRegistrationRepository userEventRegistrationRepository;


    @Override
    public UserEventRegistration createUserEventRegistration(UserEventRegistration object) {
        //test
        var userEventRegistrationRepositoryTemp = userEventRegistrationRepository.save(object);
        return object;
    }

    @Override
    public UserEventRegistration findById(Long id) {
        long idd=id;
        return userEventRegistrationRepository.findById(idd);
    }
}
