package com.lambda.NotificationService.Service;

import com.lambda.NotificationService.model.UserNotification;
import com.lambda.NotificationService.model.UserSubscription;

import java.util.List;

public interface INotificationService {
    UserNotification createUserNotification(UserNotification userNotification);
    UserSubscription createUserSubscription(UserSubscription userSubscription);
    List<UserNotification> updateSeen(Long id);
    void deleteSubscription(Long usersubscriptionid);
    boolean notifyUsersOfCreation(Long EventTypeId, String description);
   // void subscribeToACategory(Long id);
}
