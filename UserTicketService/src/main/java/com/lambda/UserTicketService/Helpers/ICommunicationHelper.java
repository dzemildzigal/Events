package com.lambda.UserTicketService.Helpers;


import com.fasterxml.jackson.core.JsonProcessingException;

public interface ICommunicationHelper {
     boolean checkIsUserAuthorized(String uid, String token);
     boolean buyATicket(Long eventId, Long userId, String token) throws JsonProcessingException;
}
