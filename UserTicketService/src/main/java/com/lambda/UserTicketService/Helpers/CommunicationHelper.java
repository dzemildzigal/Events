package com.lambda.UserTicketService.Helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.UserTicketService.Helpers.Model.BuyATicketDTO;
import com.lambda.UserTicketService.Helpers.Model.UserLoginAckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommunicationHelper implements  ICommunicationHelper {
    @Autowired
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();


    public boolean checkIsUserAuthorized(String uid, String token) {
        httpHeaders.clear();
        httpHeaders.add("Authorization",token);
        HttpEntity entity = new HttpEntity(httpHeaders);
        try {
            ResponseEntity<UserLoginAckDTO> rez = restTemplate.exchange("http://UserService/users/is-user-authorized/"+uid, HttpMethod.GET,entity,UserLoginAckDTO.class);
            return rez.getBody().isAuthenticated();
        }
        catch (Exception e) {
            return false;
        }

    }

    public boolean buyATicket(Long eventId, Long userId, String token) throws JsonProcessingException {
        try {
            httpHeaders.clear();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("Authorization", token);
            BuyATicketDTO buyATicketDTO = new BuyATicketDTO(eventId, userId, 1);
            String content = new ObjectMapper().writeValueAsString(buyATicketDTO);
            HttpEntity entity = new HttpEntity(content, httpHeaders);
            ResponseEntity<Object> rez = restTemplate.exchange("http://EventService/event/buy-ticket/", HttpMethod.PUT,entity,Object.class);
            return rez.getStatusCode() == HttpStatus.OK;
        }
        catch (Exception e ) {
            return false;
        }

    }
}
