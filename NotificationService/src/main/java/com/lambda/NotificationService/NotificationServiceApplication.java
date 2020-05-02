package com.lambda.NotificationService;
import com.lambda.NotificationService.Service.INotificationService;
import com.lambda.NotificationService.Model.Entity.UserSubscription;
import com.lambda.NotificationService.Model.Entity.UserNotification;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
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
@RestController
class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}