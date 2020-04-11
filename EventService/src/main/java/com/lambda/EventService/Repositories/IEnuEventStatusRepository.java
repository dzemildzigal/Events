package com.lambda.EventService.Repositories;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Entity.EnuEventStatus;
import org.springframework.data.repository.CrudRepository;

public interface IEnuEventStatusRepository extends CrudRepository<EnuEventStatus,Long> {
    EnuEventStatus findById(long id)throws CustomEventException;
    EnuEventStatus findByDescription(String description)throws CustomEventException;
}
