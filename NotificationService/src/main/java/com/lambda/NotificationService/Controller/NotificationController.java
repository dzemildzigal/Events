
package com.lambda.NotificationService.Controller;

import com.lambda.NotificationService.ExceptionHandling.CustomException;
import com.lambda.NotificationService.Helpers.UserServiceHelper;
import com.lambda.NotificationService.Service.INotificationService;
import com.lambda.NotificationService.Model.Api.CreatedNotificationDTO;
import com.lambda.NotificationService.Model.Entity.UserNotification;
import com.lambda.NotificationService.Model.Entity.UserSubscription;
import com.lambda.NotificationService.Model.Api.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController

@RequestMapping("notifications")

public class NotificationController {
    @Autowired
    INotificationService notificationservice;

    @Autowired
    UserServiceHelper userServiceHelper;

    @PostMapping("/newsubscription")
    public UserSubscription createSubscription(@RequestBody UserSubscription info, @RequestHeader(value = "Authorization") String authorizationToken) throws Exception{
        if(userServiceHelper.CheckUserAuthorised(info.getUserId().toString(), authorizationToken)) {
            return this.notificationservice.createUserSubscription(info);
        }
        throw new AccessDeniedException("403: User with ID=" + info.getUserId().toString() + " is unauthorized.");
    }

    @PostMapping("/newnotification")
    public UserNotification createNotification(@RequestBody UserNotification info, @RequestHeader(value = "Authorization") String authorizationToken) throws Exception{
        if(userServiceHelper.CheckUserAuthorised(info.getUserId().toString(), authorizationToken)) {
            return this.notificationservice.createUserNotification(info);
        }
        throw new AccessDeniedException("403: User with ID=" + info.getUserId().toString() + " is unauthorized.");
    }

    @PostMapping("/notify-users-of-event-creation")
    public MessageDTO notifyUsers(@RequestBody CreatedNotificationDTO notification) throws Exception{

         this.notificationservice.notifyUsersOfCreation(notification.getEventTypeId(), notification.getDescription());
         return new MessageDTO("Notifications have been created!");
    }

    @GetMapping("/get-notifications/{userId}")
    public List<UserNotification> getNotifications(@PathVariable Long userId, @RequestHeader(value = "Authorization") String authorizationToken) throws CustomException, AccessDeniedException {
        if(userServiceHelper.CheckUserAuthorised(userId.toString(), authorizationToken)) {
            return this.notificationservice.getNotifications(userId);
        }
        throw new AccessDeniedException("403: User with ID=" + userId.toString() + " is unauthorized.");
    }

    @GetMapping("/get-subscriptions/{userId}")
    public List<UserSubscription> getSubscriptions(@PathVariable Long userId, @RequestHeader(value = "Authorization") String authorizationToken) throws CustomException, AccessDeniedException {
        if(userServiceHelper.CheckUserAuthorised(userId.toString(), authorizationToken)) {
            return this.notificationservice.getSubscriptions(userId);
        }
        throw new AccessDeniedException("403: User with ID=" + userId.toString() + " is unauthorized.");
    }

    @DeleteMapping("/deletesubscription/{usersubscriptionid}")
    public void DeleteSubscription (@PathVariable Long usersubscriptionid) throws Exception{
     notificationservice.deleteSubscription(usersubscriptionid);
    }

    @PutMapping("/updateseen/{userid}")
    public List<UserNotification> updateSeen (@PathVariable Long userid, @RequestHeader(value = "Authorization") String authorizationToken) throws Exception{

        if(userServiceHelper.CheckUserAuthorised(userid.toString(), authorizationToken)) {
            return this.notificationservice.updateSeen(userid);
        }
        throw new AccessDeniedException("403: User with ID=" + userid.toString() + " is unauthorized.");
    }
}
