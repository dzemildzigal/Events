package EurekaServer;

//import com.lambda.NotificationService.EurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer

public class ServiceRegistrationAndDiscovery {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistrationAndDiscovery.class, args);
    }

}