package com.lambda.UserTicketService;

import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class UserTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTicketServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUserTicketService service) {
		return (args) -> {

			UserTicket userTicket = new UserTicket(null, 1L, 1L);
			CCPayment payment = new CCPayment(null, userTicket, new BigDecimal(22), "number");
			var tmp = service.createPaymentForTicket(payment);
		};
	}
}
