package com.example.SystemEvents.Repository;

import com.example.SystemEvents.Entity.SystemEvent;
import org.springframework.data.repository.CrudRepository;

public interface ISystemEventRepository extends CrudRepository<SystemEvent, Long> {
}
