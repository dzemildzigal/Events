package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
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
    public UserEventRegistration createUserEventRegistration(UserEventRegistration object)throws CustomEventException {
        //test
        if(object == null || object.getEnuRegistrationType() == null || object.getEvent() == null || object.getUserId() == null) throw new CustomEventException("400: One or more attributes of class UserEventRegistration (possibly whole object) is null!");
        var userEventRegistrationRepositoryTemp = userEventRegistrationRepository.save(object);
        return object;
    }

    @Override
    public UserEventRegistration findById(Long id)throws CustomEventException {
        long idd=id;
        var result = userEventRegistrationRepository.findById(idd);
        if(result != null) return result;
        throw new CustomEventException("404: UserEventRegistration with ID="+id.toString()+" does not exist in the database!");
    }
    @Override
    public List<UserEventRegistration> findByUserId(long userId)throws CustomEventException{
        Long uid = userId;
        var result = userEventRegistrationRepository.findByUserId(uid);
        if(result!=null) return result;
        throw new CustomEventException("404: List of UserEventRegistrations associated with the User with ID="+uid.toString()+" does not exist in the database!");
    }
}
