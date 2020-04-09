package com.lambda.EventService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.Controllers.EventController;
import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Helpers.UserServiceHelper;
import com.lambda.EventService.Models.EnuEventStatus;
import com.lambda.EventService.Models.Event;
import com.lambda.EventService.Models.UserLoginAckDTO;
import org.codehaus.jettison.json.JSONStringer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
		String content = objectMapper.writeValueAsString(new Event());
		this.mockMvc.perform(MockMvcRequestBuilders
				.put(URL+"/update-event?oldEventId=4").header("Authorization", "Bearer " + authToken)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void assertCanUpdateEventStatus() throws Exception{
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		String content = objectMapper.writeValueAsString(new EnuEventStatus());
		this.mockMvc.perform(MockMvcRequestBuilders
				.put(URL+"/update-status/4").header("Authorization", "Bearer " + authToken)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void assertCanAddNewEvent() throws Exception{
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		String content = objectMapper.writeValueAsString(new Event());
		this.mockMvc.perform(MockMvcRequestBuilders
				.post(URL+"/add-event").header("Authorization", "Bearer " + authToken)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
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
