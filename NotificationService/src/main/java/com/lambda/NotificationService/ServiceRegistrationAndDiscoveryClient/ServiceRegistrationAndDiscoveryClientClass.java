package com.lambda.NotificationService.ServiceRegistrationAndDiscoveryClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceRegistrationAndDiscoveryClientClass {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistrationAndDiscoveryClientClass.class, args);
    }
}
