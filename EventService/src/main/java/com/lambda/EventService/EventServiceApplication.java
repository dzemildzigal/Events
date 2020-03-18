package com.lambda.EventService;

import com.lambda.EventService.Services.IEventService;
import com.lambda.EventService.models.Event;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.lambda.EventService.repository.IEventRepository;
import java.sql.Date;

@SpringBootApplication
public class EventServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IEventService service){
		return(args) ->{
		long millis=System.currentTimeMillis();
		Event eventInfo = new Event(null,
				"Svirka Radno Vrij eme",
				"Straaaaaashna svirka, drug.",
				1L,
				1L,
				true,
				10.0D,
				100L,
				"https://www.google.ba",
				1L,
				1L,
				new java.sql.Date(millis));
		 var tmp = service.createEvent(eventInfo);
		};
	}

}
