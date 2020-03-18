package com.lambda.EventService.Services;

import com.lambda.EventService.models.UserEventRegistration;

public interface IUserEventRegistrationService {
    UserEventRegistration createUserEventRegistration(UserEventRegistration object);
    UserEventRegistration findById(Long id);

}
