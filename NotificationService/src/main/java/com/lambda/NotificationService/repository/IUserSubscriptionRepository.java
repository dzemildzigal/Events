package com.lambda.NotificationService.repository;

import com.lambda.NotificationService.model.UserSubscription;
import org.springframework.data.repository.CrudRepository;

public interface IUserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {

}
