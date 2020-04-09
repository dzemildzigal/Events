
package com.lambda.NotificationService.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.NotificationService.ExceptionHandling.CustomException;
import com.lambda.NotificationService.Helpers.UserServiceHelper;
import com.lambda.NotificationService.Service.INotificationService;
import com.lambda.NotificationService.model.CreatedNotification;
import com.lambda.NotificationService.model.UserNotification;
import com.lambda.NotificationService.model.UserSubscription;
import com.lambda.NotificationService.model.api.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController

@RequestMapping("notifications")

public class NotificationController {
    @Autowired
    INotificationService notificationservice;

    @Autowired
    UserServiceHelper userServiceHelper;

    @PostMapping("/newsubscription")
    public UserSubscription createSubscription(@RequestBody UserSubscription info) {
        return this.notificationservice.createUserSubscription(info);
    }

    @PostMapping("/newnotification")
    public UserNotification createNotification(@RequestBody UserNotification info) {
        return this.notificationservice.createUserNotification(info);

    }

    @PostMapping("/notify-users-of-event-creation")
    public MessageDTO notifyUsers(@RequestBody CreatedNotification notification){

         this.notificationservice.notifyUsersOfCreation(notification.getEventTypeId(), notification.getDescription());
         return new MessageDTO("Notifications have been created!");
    }

    @GetMapping("/get-notifications/{userId}")
    public List<UserNotification> getNotifications(@PathVariable Long userId,@RequestHeader(value = "Authorization") String authorizationToken) throws CustomException {
        if(userServiceHelper.CheckUserAuthorised(userId.toString(),authorizationToken)) {
            return this.notificationservice.getNotifications(userId);

        }
        throw new CustomException("403: User with ID="+userId.toString()+" is unauthorized.");

    }

    @GetMapping("/get-subscriptions/{userId}")
    public List<UserSubscription> getSubscriptions(@PathVariable Long userId,@RequestHeader(value = "Authorization") String authorizationToken) throws CustomException {
        if(userServiceHelper.CheckUserAuthorised(userId.toString(),authorizationToken)) {
            return this.notificationservice.getSubscriptions(userId);

        }
        throw new CustomException("403: User with ID="+userId.toString()+" is unauthorized.");

    }

    @DeleteMapping("/deletesubscription/{usersubscriptionid}")
    public void DeleteSubscription (@PathVariable Long usersubscriptionid){
     notificationservice.deleteSubscription(usersubscriptionid); }

    @PutMapping("/updateseen/{userid}")
    public List<UserNotification> updateSeen (@PathVariable Long userid){
        return this.notificationservice.updateSeen(userid);
    }


}
