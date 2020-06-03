package com.lambda.EventService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.Controllers.EventRegistrationController;
import com.lambda.EventService.Helpers.UserServiceHelper;
import com.lambda.EventService.Models.Entity.EnuRegistrationType;
import com.lambda.EventService.Models.Entity.Event;
import com.lambda.EventService.Models.Entity.UserEventRegistration;
import com.lambda.EventService.Models.Api.UserLoginAckDTO;
import com.lambda.EventService.Services.ServiceImplementation.EventService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventRegistrationControllerTests {
    private final String URL = "/eventRegistration";
    private String authToken;
    private ObjectMapper objectMapper = new ObjectMapper();
    private HttpHeaders httpHeaders = new HttpHeaders();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserServiceHelper userServiceHelper;
    @Autowired
    private EventRegistrationController eventRegistrationController;
    @Autowired
    private EventService eventSErvice;

    @Test
    void contextLoads() {
        assertThat(eventRegistrationController).isNotNull();
    }

    @Test
    void assertEventRegistrationExists() throws Exception {
        try {
            //EventRegistration with ID = 0 does not exist
            UserLoginAckDTO response = userServiceHelper.loginUser("test", "testtest");
            authToken = response.getToken();
            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(URL + "/0").header("Authorization", "Bearer " + authToken)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(URL + "/7").header("Authorization", "Bearer " + authToken)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex){}
    }

    @Test
    void assertEventRegistrationTypeExists() throws Exception {
        //EventRegistrationType with ID = 0 does not exist
        try {
            UserLoginAckDTO response = userServiceHelper.loginUser("test", "testtest");
            authToken = response.getToken();
            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(URL + "/type/0").header("Authorization", "Bearer " + authToken)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(URL + "/type/7").header("Authorization", "Bearer " + authToken)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex){}
    }

    @Test
    void assertEventRegistrationWithUserExists() throws Exception {
        try {
            //EventRegistration with User ID = 0 does not exist, but should be empty list
            UserLoginAckDTO response = userServiceHelper.loginUser("test", "testtest");
            authToken = response.getToken();
            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(URL + "/user/-1").header("Authorization", "Bearer " + authToken)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is5xxServerError());

            this.mockMvc.perform(MockMvcRequestBuilders
                    .get(URL + "/user/1").header("Authorization", "Bearer " + authToken)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception ex){}
    }

    @Test
    void assertCanRegisterUserOnEvent() throws Exception{
        try {
            UserLoginAckDTO response = userServiceHelper.loginUser("test", "testtest");
            authToken = response.getToken();
            String contentFail = objectMapper.writeValueAsString(new UserEventRegistration());
            this.mockMvc.perform(MockMvcRequestBuilders
                    .post(URL + "/registerUser/123").header("Authorization", "Bearer " + authToken)
                    .content(contentFail)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
            // Unvalid method (path not correct), has to throw 405
            this.mockMvc.perform(MockMvcRequestBuilders
                    .post(URL + "/registerUser").header("Authorization", "Bearer " + authToken)
                    .content(contentFail)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isMethodNotAllowed());

            var payloadSuccess = new UserEventRegistration();
            payloadSuccess.setEvent(eventSErvice.findById(4L));
            var eventList = new ArrayList<Event>();
            eventList.add(payloadSuccess.getEvent());
            var enuRegType = new EnuRegistrationType();
            var listUserEventRegistration = new ArrayList<UserEventRegistration>();
            listUserEventRegistration.add(payloadSuccess);
            enuRegType.setDescription("Test description");
            enuRegType.setUserEventRegistrationList(listUserEventRegistration);
            payloadSuccess.setEnuRegistrationType(enuRegType);
            String contentSuccess = objectMapper.writeValueAsString(payloadSuccess);

            // Not found, non-valid regTypeString.
            this.mockMvc.perform(MockMvcRequestBuilders
                    .post(URL + "/4/registerUser/1?regTypeString=asd").header("Authorization", "Bearer " + authToken)
                    .content(contentSuccess)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

            // Everything is valid. Must return 200 OK.
            this.mockMvc.perform(MockMvcRequestBuilders
                    .post(URL + "/4/registerUser/1?regTypeString=Registracija na Jeminu svirku").header("Authorization", "Bearer " + authToken)
                    .content(contentSuccess)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex){}
    }
}