package com.lambda.EventService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.Controllers.EventController;
import com.lambda.EventService.Helpers.NotificationServiceHelper;
import com.lambda.EventService.Helpers.UserServiceHelper;
import com.lambda.EventService.Models.Api.EnuEventStatusWrapperDTO;
import com.lambda.EventService.Models.Api.EventWrapperDTO;
import com.lambda.EventService.Models.Entity.*;
import com.lambda.EventService.Models.Api.UserLoginAckDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTests {
	private final String URL = "/event";

	private String authToken;
	private ObjectMapper objectMapper = new ObjectMapper();
	private HttpHeaders httpHeaders = new HttpHeaders();
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserServiceHelper userServiceHelper;
	@Autowired
	private EventController eventController;
	@Autowired
	private NotificationServiceHelper notificationServiceHelper;

	@Test
	void contextLoads() {
		assertThat(eventController).isNotNull();
	}

	@Test
	void assertEventExists() throws Exception {
		//Event with ID = 0 does not exist
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL + "/0").header("Authorization", "Bearer " + authToken)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/4").header("Authorization", "Bearer " + authToken)
		        .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


	@Test
	void assertEventLocationExists() throws Exception{
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		this.mockMvc.perform(MockMvcRequestBuilders
		.get(URL+"/location/0").header("Authorization", "Bearer " + authToken)
		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/location/4").header("Authorization", "Bearer " + authToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void assertEventStatusExists() throws Exception{
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/status/0").header("Authorization", "Bearer " + authToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/status/4").header("Authorization", "Bearer " + authToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void assertEventTypeExists() throws Exception{
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/type/0").header("Authorization", "Bearer " + authToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/type/4").header("Authorization", "Bearer " + authToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void assertCanUpdateEvent() throws Exception{
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		var	eventWrapper = new EventWrapperDTO();
		String contentFail = objectMapper.writeValueAsString(eventWrapper);

		//All parameters are null, meaning the request is bad.
		this.mockMvc.perform(MockMvcRequestBuilders
				.put(URL+"/update-event?oldEventId=4").header("Authorization", "Bearer " + authToken)
				.content(contentFail)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());


		var date = new Date(System.currentTimeMillis());
		var eventList = new ArrayList<Event>();
		var event = new Event(null,
				"Testing name",
				"Testing description",
				null,
				null,
				false,
				10.2,
				0L,
				"https://www.test.com",
				1L,
				null,
				date,
				null,
				null);
		eventList.add(event);
		var location = new Location(null,"Location test description",eventList);
		var eventType = new EventType(null,"Test event type description",eventList);
		var enuEventStatus = new EnuEventStatus(null,"Test enuEventStatus",eventList);
		var userEventRegistrations = new ArrayList<UserEventRegistration>();

		event.setLocation(location);
		event.setEventType(eventType);
		event.setEnuEventStatus(enuEventStatus);
		event.setUserEventRegistrationList(userEventRegistrations);
		eventWrapper.setEvent(event);
		eventWrapper.setEnuEventStatus(enuEventStatus);
		eventWrapper.setEventType(eventType);
		eventWrapper.setLocation(location);
		eventWrapper.setUserId(event.getCreatedByUserId());
		String contentSuccess = objectMapper.writeValueAsString(eventWrapper);

		//Parameters are OK, response has to be 200 OK.
		this.mockMvc.perform(MockMvcRequestBuilders
				.put(URL+"/update-event?oldEventId=4").header("Authorization", "Bearer " + authToken)
				.content(contentSuccess)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void assertCanUpdateEventStatus() throws Exception{
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		var	enuEventStatusWrapper = new EnuEventStatusWrapperDTO();
		String contentFailBadRequest = objectMapper.writeValueAsString(enuEventStatusWrapper);
		//Test for a bad request because the wrapper class is initialized with null values for its properties.
		this.mockMvc.perform(MockMvcRequestBuilders
				.put(URL+"/update-status/4").header("Authorization", "Bearer " + authToken)
				.content(contentFailBadRequest)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

		var enuEventStatus = new EnuEventStatus();
		enuEventStatus.setEvents(new ArrayList<Event>());
		enuEventStatus.setDescription("TestDesc");
		enuEventStatusWrapper.setEnuEventStatus(enuEventStatus);
		enuEventStatusWrapper.setUserId(1L);
		String contentFailNotFound = objectMapper.writeValueAsString(enuEventStatusWrapper);

		//Test for a successful status update (new status)
		this.mockMvc.perform(MockMvcRequestBuilders
				.put(URL+"/update-status/4").header("Authorization", "Bearer " + authToken)
				.content(contentFailNotFound)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());


	}

	@Test
	void assertCanAddNewEvent() throws Exception{
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		var	eventWrapper = new EventWrapperDTO();
		String contentFail = objectMapper.writeValueAsString(eventWrapper);
		this.mockMvc.perform(MockMvcRequestBuilders
				.post(URL+"/add-event").header("Authorization", "Bearer " + authToken)
				.content(contentFail)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		var date = new Date(System.currentTimeMillis());
		var eventList = new ArrayList<Event>();
		var event = new Event(null,
				"Testing name",
				"Testing description",
				null,
				null,
				false,
				10.2,
				0L,
				"https://www.test.com",
				1L,
				null,
				date,
				null,
				null);
		eventList.add(event);
		var location = new Location(null,"Location test description",eventList);
		var eventType = new EventType(null,"Test event type description",eventList);
		var enuEventStatus = new EnuEventStatus(null,"Test enuEventStatus",eventList);
		var userEventRegistrations = new ArrayList<UserEventRegistration>();

		event.setLocation(location);
		event.setEventType(eventType);
		event.setEnuEventStatus(enuEventStatus);
		event.setUserEventRegistrationList(userEventRegistrations);
		eventWrapper.setEvent(event);
		eventWrapper.setEnuEventStatus(enuEventStatus);
		eventWrapper.setEventType(eventType);
		eventWrapper.setLocation(location);
		eventWrapper.setUserId(event.getCreatedByUserId());
		String contentSuccess = objectMapper.writeValueAsString(eventWrapper);


		this.mockMvc.perform(MockMvcRequestBuilders
				.post(URL+"/add-event").header("Authorization", "Bearer " + authToken)
				.content(contentSuccess)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void assertCanDeleteEvent() throws Exception{
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		String content = objectMapper.writeValueAsString(new Event());
		this.mockMvc.perform(MockMvcRequestBuilders
				.delete(URL+"/delete-event").header("Authorization", "Bearer " + authToken)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
