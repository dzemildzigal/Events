package com.lambda.EventService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.Controllers.EventController;
import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Event;
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
class 	EventServiceApplicationTests {
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


}
