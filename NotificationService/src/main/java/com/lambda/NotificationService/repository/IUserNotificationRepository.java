package com.lambda.NotificationService.repository;

import com.lambda.NotificationService.model.UserNotification;
import org.springframework.data.repository.CrudRepository;

public interface IUserNotificationRepository extends CrudRepository<UserNotification, Long> {

}
