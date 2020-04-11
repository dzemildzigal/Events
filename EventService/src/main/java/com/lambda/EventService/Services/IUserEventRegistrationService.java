package com.lambda.EventService.Services;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Entity.UserEventRegistration;
import java.util.List;

public interface IUserEventRegistrationService {
    UserEventRegistration createUserEventRegistration(UserEventRegistration object)throws CustomEventException;
    UserEventRegistration findById(Long id)throws CustomEventException;
    List<UserEventRegistration> findByUserId(long userId)throws CustomEventException;
}
