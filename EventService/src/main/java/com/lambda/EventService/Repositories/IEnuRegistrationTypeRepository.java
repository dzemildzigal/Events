package com.lambda.EventService.Repositories;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Entity.EnuRegistrationType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEnuRegistrationTypeRepository extends CrudRepository<EnuRegistrationType,Long> {
    EnuRegistrationType findById(long id)throws CustomEventException;
    List<EnuRegistrationType> findByDescription(String description)throws CustomEventException;
}
