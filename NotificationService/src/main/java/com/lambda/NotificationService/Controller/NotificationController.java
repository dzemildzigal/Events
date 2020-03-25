
package com.lambda.NotificationService.Controller;

import com.lambda.NotificationService.Service.INotificationService;
import com.lambda.NotificationService.model.UserNotification;
import com.lambda.NotificationService.model.UserSubscription;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notifications")

public class NotificationController {
    @Autowired
    INotificationService notificationservice;
    @PostMapping("/newsubscription")
    public UserSubscription createSubscription (@RequestBody UserSubscription info) {
        return this.notificationservice.createUserSubscription(info);
    }
    @PostMapping("/newnotification")
    public UserNotification createNotification (@RequestBody UserNotification info) {
        return this.notificationservice.createUserNotification(info);
    }
    @PostMapping("/deletesubscription/{usersubscriptionid}")
            void DeleteSubscription (@PathVariable Long usersubscriptionid){
     notificationservice.deleteSubscription(usersubscriptionid); }
    @PutMapping("/updateseen/{userid}")
    public List<UserNotification> updateSeen (@PathVariable Long userid){
        return this.notificationservice.updateSeen(userid);
    }


}
