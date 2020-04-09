package com.lambda.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.UserService.Controller.UserController;
import com.lambda.UserService.model.api.UserLoginAckDTO;
import com.lambda.UserService.model.api.UserLoginDTO;
import com.lambda.UserService.model.entity.UserCredentials;
import com.lambda.UserService.model.entity.UserInfo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityNotFoundException;

import static com.lambda.UserService.Security.SecurityConstants.HEADER_PREFIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTests {
    private final String URL = "/users/";

    private ObjectMapper objectMapper = new ObjectMapper();
    private  static String authToken;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    void loginUserWrongUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(URL + "/sign-in")
                .content(objectMapper.writeValueAsString(new UserLoginDTO("x", "y")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authenticated").value(false));
    }

    @Order(1)
    @Test
    void loginUserExistingUser() throws Exception {
        String content = objectMapper.writeValueAsString(new UserLoginDTO("test", "testtest"));
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post(URL + "sign-in")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authenticated").value(true));
        String resultContent = resultActions.andReturn().getResponse().getContentAsString();
        UserLoginAckDTO dto = objectMapper.readValue(resultContent, UserLoginAckDTO.class);

        this.authToken = HEADER_PREFIX + dto.getToken();
    }

    @Order(2)
    @Test
    void getExistingUserInfo() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "/user-info/1")
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }


    @Order(3)
    @Test
    void updateUserInfo() throws Exception {
        UserInfo userInfo = new UserInfo(1L, "test1@test.com", "test", "test");
        this.mockMvc.perform(MockMvcRequestBuilders
                .put(URL + "/user-info/update")
                .content(objectMapper.writeValueAsString(userInfo))
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Order(4)
    @Test
    void updateUserInfoWrongParams() throws Exception {
        UserInfo userInfo = new UserInfo(1L, "t", "test", "test");
        this.mockMvc.perform(MockMvcRequestBuilders
                .put(URL + "/user-info/update")
                .content(objectMapper.writeValueAsString(userInfo))
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getUnauthorizedExistingUserInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "/user-info/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void isUserAuthenticated() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "is-user-authorized/1")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authToken))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authenticated").value(true));
    }

    @Test
    void isUserNotAuthenticated() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "is-user-authorized/3")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authToken))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authenticated").value(false));
    }

    @Test
    void createUser() throws Exception {
        UserInfo userInfo = new UserInfo(null, "test@test.com", "test", "test");
        UserCredentials userCredentials = new UserCredentials(null, "test1", "testtest", userInfo);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(URL + "user-info/sign-up")
                .content(objectMapper.writeValueAsString(userCredentials))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void createUserIncorrectData() throws Exception {
        UserInfo userInfo = new UserInfo(null, "test@test.com", "test", "test");
        UserCredentials userCredentials = new UserCredentials(null, null, "testtest", userInfo);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(URL + "user-info/sign-up")
                .content(objectMapper.writeValueAsString(userCredentials))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteUserInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(URL + "delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", authToken)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteUnAuthorizedUserInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(URL + "delete/3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteNotExistingUserInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(URL + "delete/333")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", authToken)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
