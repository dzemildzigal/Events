package com.lambda.UserTicketService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.UserTicketService.Controller.UserTicketController;
import com.lambda.UserTicketService.model.CCPayment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest

class UserTicketServiceApplicationTests {
	@Autowired
	private UserTicketController userTicketController;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
/*
	@Test
	void createPayment() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/usertickets/payment/create")
				.content(asJsonString(new CCPayment(null, null, null, "nesto")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}
	@Test
	void createPayment2() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/usertickets/payment/create")
				.content(asJsonString(new CCPayment(null, 2L,2500 , "12345799255658")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	void getTicketsByUserId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("usertickets/users-tickets/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	void getTicketsByUserId2() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("usertickets/users-tickets/588")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	@Test
	void getTicketsById() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("usertickets/588")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	@Test
	void getTicketsById2() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("usertickets/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}*/
}