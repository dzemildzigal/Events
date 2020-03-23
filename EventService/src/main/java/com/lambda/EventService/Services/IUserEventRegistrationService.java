package com.lambda.EventService.Services;

import com.lambda.EventService.Models.UserEventRegistration;

public interface IUserEventRegistrationService {
    UserEventRegistration createUserEventRegistration(UserEventRegistration object);
    UserEventRegistration findById(Long id);

}
