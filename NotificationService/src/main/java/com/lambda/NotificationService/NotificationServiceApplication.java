package com.lambda.NotificationService;
import com.lambda.NotificationService.Service.INotificationService;
import com.lambda.NotificationService.model.UserSubscription;
import com.lambda.NotificationService.model.UserNotification;
import com.lambda.NotificationService.repository.IUserNotificationRepository;
import com.lambda.NotificationService.repository.IUserSubscriptionRepository;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;


@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(INotificationService service) {
        return (args) -> {

             UserNotification usernotification = new UserNotification(1L,1L,"Yesterday all my troubles seemed so far away",true);
             UserSubscription usersubscription = new UserSubscription(1L,1L,1L);
            var x =  service.createUserNotification(usernotification);
            var y = service.createUserSubscription(usersubscription);
        };
    }
}
