package com.lambda.EventService;

import com.lambda.EventService.Models.EnuEventStatus;
import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Models.EventType;
import com.lambda.EventService.Services.IEnuEventStatusService;
import com.lambda.EventService.Services.IEventService;
import com.lambda.EventService.Services.IEventTypeService;
import com.lambda.EventService.Services.ILocationService;
import com.lambda.EventService.Models.Location;
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
	public CommandLineRunner insertNewLocation(ILocationService service){
		return(args) ->{
			long millis=System.currentTimeMillis();
			Location lokacija = new Location(null,"Meee, strasna lokacija drug.");
			var tmpLocation = service.createLocation(lokacija);
		};
	}
	@Bean
	public CommandLineRunner insertNewEventType(IEventTypeService service){
		return(args) ->{
			long millis=System.currentTimeMillis();
			EventType tip = new EventType(null,"Tjentiste, almemi",null);
			var tmpEventType = service.createEventType(tip);
		};
	}
	@Bean
	public CommandLineRunner insertNewEnuEventStatus(IEnuEventStatusService service){
		return(args) ->{
			long millis=System.currentTimeMillis();
			EnuEventStatus eES = new EnuEventStatus(null,"Aktivan",null);
			var tmpEnuEventStatus = service.createEnuEventStatus(eES);
		};
	}
	@Bean
	public CommandLineRunner insertNewEvent(IEventService service,ILocationService locationService, IEventTypeService eventTypeService, IEnuEventStatusService enuEventStatusService){
		return(args) ->{
			long millis=System.currentTimeMillis();
			Location lokacija = new Location(null,"Meee, strasna lokacija drug.");
			var tmpLocation = locationService.createLocation(lokacija);
			EventType tip = new EventType(null,"Tjentiste, almemi",null);
			var tmpEventType = eventTypeService.createEventType(tip);
			EnuEventStatus eES = new EnuEventStatus(null,"Aktivan",null);
			var tmpEnuEventStatus = enuEventStatusService.createEnuEventStatus(eES);
			Event eventInfo = new Event(null,
				"Svirka Radno Vrijeme",
				"Straaaaaashna svirka, drug.",
				tmpLocation,
				tmpEventType,
				true,
				10.0D,
				100L,
				"https://www.google.ba",
				1L,
				tmpEnuEventStatus,
				new java.sql.Date(millis),
					null,null);
				var tmpEvent = service.createEvent(eventInfo);
		};
	}

}
