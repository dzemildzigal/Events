package com.lambda.NotificationService.repository;

import com.lambda.NotificationService.model.UserSubscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {
List<UserSubscription> findByEventTypeId (Long eventTypeId);
    List<UserSubscription> findByUserId(Long userId);
}
