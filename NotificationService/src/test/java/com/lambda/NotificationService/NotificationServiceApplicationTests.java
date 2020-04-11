package com.lambda.NotificationService;
import com.lambda.NotificationService.Helpers.UserServiceHelper;
import com.lambda.NotificationService.Model.Entity.UserSubscription;
import com.lambda.NotificationService.Model.Api.UserLoginAckDTO;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.NotificationService.Controller.NotificationController;
import com.lambda.NotificationService.Model.Entity.UserNotification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest
class NotificationServiceApplicationTests {

	final String url = "/notifications/";

	private  static String authToken;

	@Autowired
	private NotificationController notificationController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserServiceHelper userServiceHelper;

	@Test
	void contextLoads() throws Exception {
	}

	@Test
	void createNotificationWithError() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/notifications/newnotification")
				.content(asJsonString(new UserNotification(null, null,null,false)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void createNotificationSuccessful() throws Exception {
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		mockMvc.perform(MockMvcRequestBuilders
				.post("/notifications/newnotification").header("Authorization", "Bearer " + authToken)
				.content(asJsonString(new UserNotification(null, 1L,"SpringBoot",false)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void createSubscriptionWithError() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/notifications/newsubscription")
				.content(asJsonString(new UserSubscription(null, null,null)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void createSubscriptionSuccessful() throws Exception {
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();

		mockMvc.perform(MockMvcRequestBuilders
				.post("/notifications/newsubscription").header("Authorization", "Bearer " + authToken)
				.content(asJsonString(new UserSubscription(null, 1L,3L)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteSubscriptionSuccessful() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/notifications/deletesubscription/2")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}

	@Test
	public void deleteSubscriptionWithError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/notifications/deletesubscription/126")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void updateSeen() throws Exception {
		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();
		mockMvc.perform(MockMvcRequestBuilders
				.put("/notifications/updateseen/1").header("Authorization", "Bearer " + authToken)
				.content(asJsonString(new UserSubscription(1L,1L ,2L)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

		@Test
		void AuthorizationTestWithGetNotificationsSuccessful() throws Exception{

		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
			authToken = response.getToken();

			this.mockMvc.perform(MockMvcRequestBuilders
					.get("/notifications/get-notifications/1").header("Authorization", "Bearer " + authToken)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		}

	@Test
	void AuthorizationTestWithGetNotificationsWithError() throws Exception{

		UserLoginAckDTO response = userServiceHelper.loginUser("Lejla","testtest");
		authToken = response.getToken();

		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/notifications/get-notifications/1").header("Authorization", "Bearer " + authToken)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}

	@Test
	void AuthorizationTestGetSubscriptionsWithError() throws Exception{

		UserLoginAckDTO response = userServiceHelper.loginUser("Lejla","testtest");
		authToken = response.getToken();

		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/notifications/get-subscriptions/1").header("Authorization", "Bearer " + authToken)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}

	@Test
	void AuthorizationTestGetSubscriptionsSuccessful() throws Exception{

		UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
		authToken = response.getToken();

		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/notifications/get-subscriptions/1").header("Authorization", "Bearer " + authToken)
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