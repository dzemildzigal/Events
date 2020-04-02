package com.lambda.UserTicketService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserTicketServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	/*
	@Test
	void createNotification() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/notifications/newnotification")
				.content(asJsonString(new UserNotification(null, null,null,false)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}*/
}
