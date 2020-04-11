package com.lambda.UserTicketService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lambda.UserTicketService.Model.CCPayment;
import com.lambda.UserTicketService.Model.UserTicket;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface IUserTicketService {

    UserTicket getUserTicketById(long userTicketId, String authorizationToken) throws AccessDeniedException;
    CCPayment createPaymentForTicket(CCPayment ccPayment, String authorizationToken) throws AccessDeniedException, JsonProcessingException;
    UserTicket createUserTicket(UserTicket userTicket);
    List<UserTicket> getUserTicketsByUserId(long userId, String authorizationToken) throws AccessDeniedException;
    List<UserTicket> getUserTicketsByEventId(long eventid);
}
