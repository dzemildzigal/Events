package com.lambda.EventService.repository;

import com.lambda.EventService.models.EnuEventStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEnuEventStatusRepository extends CrudRepository<EnuEventStatus,Long> {
    EnuEventStatus findById(long id);
}
