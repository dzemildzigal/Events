package com.lambda.EventService.Repositories;

import com.lambda.EventService.Models.EnuRegistrationType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEnuRegistrationTypeRepository extends CrudRepository<EnuRegistrationType,Long> {
    EnuRegistrationType findById(long id);
    List<EnuRegistrationType> findByDescription(String description);
}
