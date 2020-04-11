package com.lambda.EventService.Repositories;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Entity.UserEventRegistration;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface IUserEventRegistrationRepository extends CrudRepository<UserEventRegistration,Long> {
    UserEventRegistration findById(long id)throws CustomEventException;
    List<UserEventRegistration> findByUserId(long userId)throws CustomEventException;
}
