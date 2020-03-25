package com.lambda.EventService.Repositories;

import com.lambda.EventService.Models.UserEventRegistration;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface IUserEventRegistrationRepository extends CrudRepository<UserEventRegistration,Long> {
    UserEventRegistration findById(long id);
    List<UserEventRegistration> findByUserId(long userId);
}
