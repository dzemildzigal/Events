package com.lambda.NotificationService.Repository;

import com.lambda.NotificationService.Model.Entity.UserNotification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserNotificationRepository extends CrudRepository<UserNotification, Long> {

    List<UserNotification> findByUserId(Long userId);

}
