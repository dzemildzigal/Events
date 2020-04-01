package com.lambda.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.UserService.Controller.UserController;
import com.lambda.UserService.model.api.UserLoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    private final String URL = "/users/";

    private ObjectMapper objectMapper = new ObjectMapper();


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

    @Test
    void loginUserExistingUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(URL + "/sign-in")
                .content(objectMapper.writeValueAsString(new UserLoginDTO("test", "testtest")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authenticated").value(true));
    }
}
