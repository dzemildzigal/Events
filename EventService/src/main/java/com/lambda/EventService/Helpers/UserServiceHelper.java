package com.lambda.EventService.Helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.UserLoginAckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceHelper {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders httpHeaders = new HttpHeaders();


    public boolean CheckUserAuthorised(String uid, String token) {
        httpHeaders.clear();
        httpHeaders.add("Authorization",token);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<UserLoginAckDTO> rez = restTemplate.exchange("http://UserService/users/is-user-authorized/"+uid, HttpMethod.GET,entity,UserLoginAckDTO.class);
        return rez.getBody().isAuthenticated();
    }
    public UserLoginAckDTO loginUser(String username, String password) throws Exception {
        httpHeaders.clear();
        httpHeaders.add("Content-Type","application/json");
        HttpEntity entity = new HttpEntity(httpHeaders);
        ObjectMapper payload = new ObjectMapper();
        Map<String,String> info = new HashMap<String,String>();
        info.put("username", username);
        info.put("password", password);
        payload.writeValueAsString(info);
        try {
            ResponseEntity<UserLoginAckDTO> rez = restTemplate.exchange("http://UserService/users/sign-in", HttpMethod.POST, entity, UserLoginAckDTO.class, payload);
            return rez.getBody();
        }catch (Exception ex){
            var a = ex.getMessage();
            a.length();
        }
        throw new CustomEventException("500: Whoopsie!");
    }

}
