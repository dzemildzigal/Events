package com.lambda.UserTicketService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.UserTicketService.Controller.UserTicketController;
import com.lambda.UserTicketService.Helpers.CommunicationHelper;
import com.lambda.UserTicketService.Helpers.ICommunicationHelper;
import com.lambda.UserTicketService.Helpers.model.UserLoginAckDTO;
import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.Service.ServiceImplementation.UserTicketService;
import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;
import org.apache.http.client.methods.HttpHead;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
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
	void createPayment() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/usertickets/payment/create")
				.content(asJsonString(new CCPayment(null, null, null, "1")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void createPayment2() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/usertickets/payment/create")
				.content(asJsonString(new CCPayment(null, new UserTicket(null, 1L, 1L ), BigDecimal.TEN , "12345799255658")))
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", auth)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getTicketsByUserId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("/usertickets/users-tickets/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getTicketsByUserId2() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("/usertickets/users-tickets/588")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	void getTicketsById() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("/usertickets/588")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void getTicketsById2() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("/usertickets/1")
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