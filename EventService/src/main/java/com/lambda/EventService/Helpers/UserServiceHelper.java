package com.lambda.EventService.Helpers;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Api.UserLoginAckDTO;
import com.lambda.EventService.Models.Api.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
        //httpHeaders.add("Content-Type","application/json");
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<UserLoginAckDTO> rez = restTemplate.exchange("http://UserService/users/is-user-authorized/"+uid, HttpMethod.GET,entity,UserLoginAckDTO.class);
        return rez.getBody().isAuthenticated();
    }
    public UserLoginAckDTO loginUser(String username, String password) throws Exception {
        httpHeaders.clear();
        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        UserLoginDTO info = new UserLoginDTO(username,password);
        HttpEntity<UserLoginDTO> entity = new HttpEntity<>(info,httpHeaders);
        try {
            ResponseEntity<UserLoginAckDTO> rez = restTemplate.exchange(
                    "http://UserService/users/sign-in",
                         HttpMethod.POST,
                         entity,
                         UserLoginAckDTO.class);
            return rez.getBody();
        }catch (Exception ex){
            var a = ex.getMessage();
            a.length();
            throw new CustomEventException(a);
        }

    }

}
