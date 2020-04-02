package com.lambda.NotificationService.Service.ServiceImplementation;

import com.lambda.NotificationService.Service.INotificationService;
import com.lambda.NotificationService.model.UserNotification;
import com.lambda.NotificationService.model.UserSubscription;
import com.lambda.NotificationService.repository.IUserNotificationRepository;
import com.lambda.NotificationService.repository.IUserSubscriptionRepository;
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
    public UserSubscription createUserSubscription(UserSubscription userSubscription) {

       UserSubscription createdUserSubscription = this.userSubscriptionRepository.save(userSubscription);

       return createdUserSubscription;
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
