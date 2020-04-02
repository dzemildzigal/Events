package com.lambda.NotificationService;
import com.lambda.NotificationService.model.UserSubscription;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.NotificationService.Controller.NotificationController;
import com.lambda.NotificationService.model.UserNotification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest
class NotificationServiceApplicationTests {
	final String url = "/notifications/";
	@Autowired
	private NotificationController notificationController;
	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() throws Exception {
	}
	@Test
	void createNotification() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/notifications/newnotification")
				.content(asJsonString(new UserNotification(null, null,null,false)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}
	@Test
	void createNotification2() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/notifications/newnotification")
				.content(asJsonString(new UserNotification(null, 2L,"SpringBoot",false)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void createSubscription() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/notifications/newsubscription")
				.content(asJsonString(new UserSubscription(null, null,null)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}
	@Test
	void createSubscription2() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/notifications/newsubscription")
				.content(asJsonString(new UserSubscription(null, 2L,3L)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteSubscription() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/notifications/deletesubscription/2")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	@Test
	public void deleteSubscription2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/notifications/deletesubscription/126")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}
	@Test
	void updateSeen() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.put("/notifications/updateseen/2")
				.content(asJsonString(new UserSubscription(1L,1L ,2L)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}}