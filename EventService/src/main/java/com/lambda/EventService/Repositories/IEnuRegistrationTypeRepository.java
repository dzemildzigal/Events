package com.lambda.EventService.Repositories;

import com.lambda.EventService.Models.EnuRegistrationType;
import org.springframework.data.repository.CrudRepository;

public interface IEnuRegistrationTypeRepository extends CrudRepository<EnuRegistrationType,Long> {
    EnuRegistrationType findById(long id);
}
