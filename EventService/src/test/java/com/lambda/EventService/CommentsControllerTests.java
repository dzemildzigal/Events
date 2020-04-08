package com.lambda.EventService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.Controllers.CommentsController;
import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Helpers.UserServiceHelper;
import com.lambda.EventService.Models.EventComments;
import com.lambda.EventService.Models.UserLoginAckDTO;
import org.codehaus.jettison.json.JSONStringer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentsControllerTests {
    private final String URL = "/comment";
    private String authToken;
    private ObjectMapper objectMapper = new ObjectMapper();
    private HttpHeaders httpHeaders = new HttpHeaders();
    @Autowired
    private UserServiceHelper userServiceHelper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentsController CommentsController;

    @Test
    @Order(1)
    void contextLoads() {
        assertThat(CommentsController).isNotNull();
    }

    @Test
    @Order(2)
    void assertCommentsFromUserExists() throws Exception{
        UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
        authToken = response.getToken();
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/user/0").header("Authorization", "Bearer " + authToken)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/user/1").header("Authorization", "Bearer " + response.getToken())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void assertCommentsExists() throws Exception {
        UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
        authToken = response.getToken();
        //Comments with ID = 0 does not exist
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "/0").header("Authorization", "Bearer " + authToken)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/5").header("Authorization", "Bearer " + authToken)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    @Test
    @Order(4)
    void assertCommentsContainingStringExists() throws Exception{
        UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
        authToken = response.getToken();
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/containing/Strasna svirka, drug").header("Authorization", "Bearer " + authToken)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/containing/blabla").header("Authorization", "Bearer " + authToken)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void assertCommentsExistForEventExists() throws Exception{
        UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
        authToken = response.getToken();
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/event/0").header("Authorization", "Bearer " + authToken)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/event/4").header("Authorization", "Bearer " + authToken)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @Order(6)
    void assertCanPostNewComments() throws Exception{
        UserLoginAckDTO response = userServiceHelper.loginUser("test","testtest");
        authToken = response.getToken();
        Map<String,String> payload = new HashMap<String,String>();
        payload.put("userId","1");
        payload.put("text","ma prejako, meeeeee");
        String content = objectMapper.writeValueAsString(payload);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(URL+"/post-comment")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + authToken)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(URL+"/post-comment/4")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + authToken)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
