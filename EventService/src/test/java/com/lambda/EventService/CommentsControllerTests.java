package com.lambda.EventService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.Controllers.CommentsController;
import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.EventComments;
import org.codehaus.jettison.json.JSONStringer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommentsControllerTests {
    private final String URL = "/comment";

    private ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentsController CommentsController;

    @Test
    void contextLoads() {
        assertThat(CommentsController).isNotNull();
    }

    @Test
    void assertCommentsExists() throws Exception {
        //Comments with ID = 0 does not exist
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "/0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void assertCommentsFromUserExists() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/user/0")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/user/1")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void assertCommentsContainingStringExists() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/containing/Strasna svirka, drug")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/containing/blabla")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void assertCommentsExistForEventExists() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/event/0")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL+"/event/4")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void assertCanPostNewComments() throws Exception{
        String content = objectMapper.writeValueAsString(new EventComments());
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(URL+"/post-comment")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(URL+"/post-comment/4")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }



}
