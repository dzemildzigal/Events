package com.lambda.EventService.Services;

import com.lambda.EventService.models.UserEventRegistration;

public interface IUserEventRegistrationService {
    void createUserEventRegistration(UserEventRegistration object);
    UserEventRegistration findById(long id);

}
