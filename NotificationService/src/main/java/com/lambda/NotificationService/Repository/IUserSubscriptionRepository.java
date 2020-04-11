package com.lambda.NotificationService.Repository;

import com.lambda.NotificationService.Model.Entity.UserSubscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {
List<UserSubscription> findByEventTypeId (Long eventTypeId);
    List<UserSubscription> findByUserId(Long userId);
}
