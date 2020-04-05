package com.lambda.EventService.Helpers;

import com.lambda.EventService.Models.UserLoginAckDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceHelper {

    @Autowired
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();

    public boolean CheckUserAuthorised(String uid, String token) {
        httpHeaders.add("Authorization",token);
        //httpHeaders.add("Content-Type","application/json");
        //httpHeaders.add("Accept","*/*");
        //    UserLoginAckDTO rez = restTemplate.getForObject("http://localhost:8080/users/is-user-authorized/"+uid,UserLoginAckDTO.class,httpHeaders);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<UserLoginAckDTO> rez = restTemplate.exchange("http://UserService/users/is-user-authorized/"+uid, HttpMethod.GET,entity,UserLoginAckDTO.class);
        var res = rez.getClass();
        return rez.getBody().isAuthenticated();
    }


}
