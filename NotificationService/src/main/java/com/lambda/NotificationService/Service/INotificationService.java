package com.lambda.NotificationService.Service;

import com.lambda.NotificationService.Model.Entity.UserNotification;
import com.lambda.NotificationService.Model.Entity.UserSubscription;

import java.util.List;

public interface INotificationService {
    UserNotification createUserNotification(UserNotification userNotification);
    UserSubscription createUserSubscription(UserSubscription userSubscription);
    List<UserNotification> updateSeen(Long id);
    void deleteSubscription(Long usersubscriptionid);
    boolean notifyUsersOfCreation(Long EventTypeId, String description);
    List<UserNotification> getNotifications(Long userId);
    List<UserSubscription> getSubscriptions(Long userId);

}
