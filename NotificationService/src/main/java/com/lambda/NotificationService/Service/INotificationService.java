package com.lambda.NotificationService.Service;

import com.lambda.NotificationService.model.UserNotification;
import com.lambda.NotificationService.model.UserSubscription;

public interface INotificationService {
    UserNotification createUserNotification(UserNotification userNotification);
    UserSubscription createUserSubscription(UserSubscription userSubscription);
}
