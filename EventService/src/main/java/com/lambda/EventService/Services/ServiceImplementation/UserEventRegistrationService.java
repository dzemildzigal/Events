package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.Models.EnuRegistrationType;
import com.lambda.EventService.Services.IUserEventRegistrationService;
import com.lambda.EventService.Models.UserEventRegistration;
import com.lambda.EventService.Repositories.IUserEventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    @Override
    public List<UserEventRegistration> findByUserId(long userId){
        long uid = userId;
        return userEventRegistrationRepository.findByUserId(uid);
    }
}
