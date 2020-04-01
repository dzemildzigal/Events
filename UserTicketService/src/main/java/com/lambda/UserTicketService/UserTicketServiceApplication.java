package com.lambda.UserTicketService;

import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class UserTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTicketServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUserTicketService service) {
		return (args) -> {

			UserTicket userTicket = new UserTicket(null, 1L, 1L);
			CCPayment payment = new CCPayment(null, userTicket, new BigDecimal(22), "1111111111");
			var tmp = service.createPaymentForTicket(payment);
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
