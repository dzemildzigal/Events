package com.lambda.EventService.Services;

import com.lambda.EventService.Models.UserEventRegistration;
import java.util.List;

public interface IUserEventRegistrationService {
    UserEventRegistration createUserEventRegistration(UserEventRegistration object);
    UserEventRegistration findById(Long id);
    List<UserEventRegistration> findByUserId(long userId);
}
