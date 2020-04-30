package com.example.SystemEvents;

import com.example.SystemEvents.Services.HelloWorldServiceImplementation;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.List;
@SpringBootApplication
public class SystemEventsApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SystemEventsApplication.class, args);
	}
}

