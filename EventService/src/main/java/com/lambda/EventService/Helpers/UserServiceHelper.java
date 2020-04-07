package com.lambda.EventService.Helpers;

import com.lambda.EventService.Models.UserLoginAckDTO;
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
        httpHeaders.clear();
        httpHeaders.add("Authorization",token);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<UserLoginAckDTO> rez = restTemplate.exchange("http://UserService/users/is-user-authorized/"+uid, HttpMethod.GET,entity,UserLoginAckDTO.class);
        return rez.getBody().isAuthenticated();
    }


}
