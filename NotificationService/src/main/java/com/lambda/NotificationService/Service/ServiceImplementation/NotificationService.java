package com.lambda.NotificationService.Service.ServiceImplementation;

import com.lambda.NotificationService.Service.INotificationService;
import com.lambda.NotificationService.Model.Entity.UserNotification;
import com.lambda.NotificationService.Model.Entity.UserSubscription;
import com.lambda.NotificationService.Repository.IUserNotificationRepository;
import com.lambda.NotificationService.Repository.IUserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    IUserNotificationRepository userNotificationRepository;

    @Autowired
    IUserSubscriptionRepository userSubscriptionRepository;

    @Override
    public UserNotification createUserNotification(UserNotification userNotification) {

        UserNotification createdUserNotification = this.userNotificationRepository.save(userNotification);

        return createdUserNotification;

    }

    @Override
    public List<UserNotification> getNotifications(Long userId)
    {

        return this.userNotificationRepository.findByUserId(userId);

    }

    @Override
    public List<UserSubscription> getSubscriptions(Long userId)
    {

        return this.userSubscriptionRepository.findByUserId(userId);

    }

    @Override
    public UserSubscription createUserSubscription(UserSubscription userSubscription) {

       List<UserSubscription> subscriptions = this.userSubscriptionRepository.findByUserId(userSubscription.getUserId());
       for(int i=0; i<subscriptions.size(); ++i){
            if(subscriptions.get(i).getEventTypeId().equals(userSubscription.getEventTypeId())){
                return userSubscription;
            }
       }
       UserSubscription createdUserSubscription = this.userSubscriptionRepository.save(userSubscription);

       return createdUserSubscription;
    }

    @Override
    public boolean notifyUsersOfCreation(Long EventTypeId, String description){
        List<UserSubscription> lista = userSubscriptionRepository.findByEventTypeId(EventTypeId);

        for(int i=0;i< lista.size();i++){
            UserNotification usernotification = new UserNotification(null,lista.get(i).getUserId(),description,false);
            usernotification = this.userNotificationRepository.save(usernotification);
        }
         return true;
    }

    @Override
    public List<UserNotification> updateSeen(Long id) {

          List<UserNotification> lista = userNotificationRepository.findByUserId(id);

        for (int i=0; i < lista.size(); i++){
            lista.get(i).setSeen(true);

        }
        userNotificationRepository.saveAll(lista);
        return lista;
    }

    @Override
    public void deleteSubscription(Long usersubscriptionid) {

        userSubscriptionRepository.deleteById(usersubscriptionid);

    }

}
