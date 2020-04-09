package com.lambda.NotificationService.repository;

import com.lambda.NotificationService.model.UserNotification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserNotificationRepository extends CrudRepository<UserNotification, Long> {

    List<UserNotification> findByUserId(Long userId);

}
