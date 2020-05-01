package com.lambda.EventService;

import com.lambda.EventService.Models.Entity.*;
import com.lambda.EventService.Services.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class EventServiceApplication {

	private java.util.List<UserEventRegistration> listUserEventRegistration;


	public static void main(String[] args) {
		SpringApplication.run(EventServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return  new RestTemplate();
	}

	@Bean
	public ManagedChannel getManagedChannel(){
		return ManagedChannelBuilder.forAddress("localhost",8086).usePlaintext().build();
	}

	@Bean
	public CommandLineRunner insertNewEvent(IEventService service,
											ILocationService locationService,
											IEventTypeService eventTypeService,
											IEnuEventStatusService enuEventStatusService,
											IEventCommentsService eventCommentsService,
											IEnuRegistrationTypeService enuRegistrationTypeService,
											IUserEventRegistrationService userEventRegistrationService){
		return(args) ->{

			long millis=System.currentTimeMillis();
			Location lokacija = new Location(null,"Meee, strasna lokacija drug.");
			Location tmpLocation = locationService.createLocation(lokacija);
			EventType tip = new EventType(null,"Tjentiste, almemi",null);
			EventType tmpEventType = eventTypeService.createEventType(tip);
			EnuEventStatus eES = new EnuEventStatus(null,"Aktivan",null);
			EnuEventStatus tmpEnuEventStatus = enuEventStatusService.createEnuEventStatus(eES);
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
			EventComments komentar = new EventComments(null,
					eventInfo.getCreatedByUserId(),
					eventInfo,
					"Strashna je svirka bila, drug");
			var eventCommentTmp  = eventCommentsService.createEventComments(komentar);

			EnuRegistrationType tipRegistracije = new EnuRegistrationType(null,"Registracija na Jeminu svirku",listUserEventRegistration);
			UserEventRegistration registracija = new UserEventRegistration(null,1L,tipRegistracije,eventInfo);
			var tipRegistracijeTmp = enuRegistrationTypeService.createEnuRegistrationType(tipRegistracije);
			var registracijaTmp = userEventRegistrationService.createUserEventRegistration(registracija);

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
