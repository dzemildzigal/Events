package com.lambda.EventService.Repositories;

import com.lambda.EventService.Models.UserEventRegistration;
import org.springframework.data.repository.CrudRepository;

public interface IUserEventRegistrationRepository extends CrudRepository<UserEventRegistration,Long> {
    UserEventRegistration findById(long id);
}
