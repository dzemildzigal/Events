package com.lambda.EventService;

import com.lambda.EventService.Services.IEventService;
import com.lambda.EventService.models.EnuEventStatus;
import com.lambda.EventService.models.Event;
import com.lambda.EventService.models.EventType;
import com.lambda.EventService.models.Location;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IEventService service){
		return(args) ->{
		long millis=System.currentTimeMillis();

		Location lokacija = new Location(1l,"Meee, strasna lokacija drug.");
		EventType tip = new EventType(1L,"Tjentiste, almemi");
			EnuEventStatus eES = new EnuEventStatus(1L,"Aktivan");
			Event eventInfo = new Event(1L,
				"Svirka Radno Vrijeme",
				"Straaaaaashna svirka, drug.",
				lokacija,
				tip,
				true,
				10.0D,
				100L,
				"https://www.google.ba",
				1L,
				eES,
				new java.sql.Date(millis));
		 var tmp = service.createEvent(eventInfo);
		};
	}

}
