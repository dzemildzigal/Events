package com.lambda.EventService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.Controllers.EventController;
import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.EnuEventStatus;
import com.lambda.EventService.Models.Event;
import org.codehaus.jettison.json.JSONStringer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

	private ObjectMapper objectMapper = new ObjectMapper();


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EventController eventController;

	@Test
	void contextLoads() {
		assertThat(eventController).isNotNull();
	}

	@Test
	void assertEventExists() throws Exception {
		//Event with ID = 0 does not exist
		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL + "/0")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/4")
		        .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void assertEventLocationExists() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders
		.get(URL+"/location/0")
		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/location/4")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void assertEventStatusExists() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/status/0")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/status/4")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void assertEventTypeExists() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/type/0")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		this.mockMvc.perform(MockMvcRequestBuilders
				.get(URL+"/type/4")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void assertCanUpdateEvent() throws Exception{
		String content = objectMapper.writeValueAsString(new Event());
		this.mockMvc.perform(MockMvcRequestBuilders
				.put(URL+"/update-event?oldEventId=4")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void assertCanUpdateEventStatus() throws Exception{
		String content = objectMapper.writeValueAsString(new EnuEventStatus());
		this.mockMvc.perform(MockMvcRequestBuilders
				.put(URL+"/update-status/4")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void assertCanAddNewEvent() throws Exception{
		String content = objectMapper.writeValueAsString(new Event());
		this.mockMvc.perform(MockMvcRequestBuilders
				.post(URL+"/add-event")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void assertCanDeleteEvent() throws Exception{
		String content = objectMapper.writeValueAsString(new Event());
		this.mockMvc.perform(MockMvcRequestBuilders
				.delete(URL+"/delete-event")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
