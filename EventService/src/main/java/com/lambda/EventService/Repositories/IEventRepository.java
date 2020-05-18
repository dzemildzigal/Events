package com.lambda.EventService.Repositories;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Api.EventFilterDTO;
import com.lambda.EventService.Models.Entity.Event;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IEventRepository extends CrudRepository<Event,Long>, JpaSpecificationExecutor<Event> {
    Event findById(long id)throws CustomEventException;
    @NotNull List<Event> findAll(Specification<Event> spec);
}
