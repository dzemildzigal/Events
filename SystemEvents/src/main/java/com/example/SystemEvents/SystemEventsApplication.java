package com.example.SystemEvents;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;




@SpringBootApplication
@EnableEurekaClient
public class SystemEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemEventsApplication.class, args);
	}
}

