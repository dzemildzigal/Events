package com.lambda.EventService.Repositories;

import com.lambda.EventService.Models.EnuEventStatus;
import org.springframework.data.repository.CrudRepository;

public interface IEnuEventStatusRepository extends CrudRepository<EnuEventStatus,Long> {
    EnuEventStatus findById(long id);
    EnuEventStatus findByDescription(String description);
}
