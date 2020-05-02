package com.lambda.UserTicketService;

import com.lambda.UserTicketService.Model.CCPayment;
import com.lambda.UserTicketService.Model.UserTicket;
import com.lambda.UserTicketService.Repository.ICCPaymentRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class UserTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTicketServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return  new RestTemplate();
	}
	@Bean
	public CommandLineRunner demo(ICCPaymentRepository service) {
		return (args) -> {

			UserTicket userTicket = new UserTicket(null, 1L, 1L);
			CCPayment payment = new CCPayment(null, userTicket, new BigDecimal(22), "1111111111");
			var tmp = service.save(payment);
		};
	}
}
@RestController
class ServiceInstanceRestController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(
			@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}
}
