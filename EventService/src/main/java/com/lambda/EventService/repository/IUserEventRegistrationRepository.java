package com.lambda.EventService.repository;

import com.lambda.EventService.models.UserEventRegistration;
import org.springframework.data.repository.CrudRepository;

public interface IUserEventRegistrationRepository extends CrudRepository<UserEventRegistration,Long> {

}
