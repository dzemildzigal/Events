package com.lambda.UserTicketService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.UserTicketService.Helpers.Model.UserLoginAckDTO;
import com.lambda.UserTicketService.Model.CCPayment;
import com.lambda.UserTicketService.Model.UserTicket;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTicketServiceApplicationTests {
	private static String auth;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RestTemplate restTemplate;

	@Test
	@Order(1)
	 void getAuthToken() throws JsonProcessingException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type","application/json");

		String body = "{\"username\":\"test\", \"password\":\"testtest\"}";
		HttpEntity entity = new HttpEntity(body, httpHeaders);
		ResponseEntity<UserLoginAckDTO> rez = restTemplate.exchange("http://UserService/users/sign-in", HttpMethod.POST, entity, UserLoginAckDTO.class);
		auth = "Bearer " + rez.getBody().getToken();
	}


	@Test
	void createPaymentWrongInputData() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/usertickets/payment/create")
				.content(asJsonString(new CCPayment(null, null, null, "1")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@Order(7)
	void createPaymentWrongInputDataEventIdMissing() throws Exception {
		// test added to handle not existing event exception in eventService
		UserTicket userTicket = new UserTicket(null, 1L, 9999L);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/usertickets/payment/create")
				.content(asJsonString(new CCPayment(null, userTicket, BigDecimal.TEN, "1")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", auth))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@Order(2)
	void createPaymentSuccessfully() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/usertickets/payment/create")
				.content(asJsonString(new CCPayment(null, new UserTicket(null, 1L, 4L ), BigDecimal.TEN , "12345799255658")))
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", auth)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	void getTicketsByUserId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("/usertickets/users-tickets/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", auth))
				.andExpect(status().isOk());
	}

	@Test
	void getTicketsByUserIdNotAuthorized() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("/usertickets/users-tickets/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@Order(6)
	void getNotExistingUserTicketsById() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("/usertickets/users-tickets/588")
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", auth))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@Order(5)
	void getTicketByIdSuccessfully() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("/usertickets/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", auth)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}