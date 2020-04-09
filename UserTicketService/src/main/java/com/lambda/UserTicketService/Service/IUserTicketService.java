package com.lambda.UserTicketService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface IUserTicketService {

    UserTicket getUserTicketById(long id);
    CCPayment createPaymentForTicket(CCPayment ccPayment, String authorizationToken) throws AccessDeniedException, JsonProcessingException;
    UserTicket createUserTicket(UserTicket userTicket);
    List<UserTicket> getUserTicketsByUserId(long id);
    List<UserTicket> getUserTicketsByEventId(long eventid);
}
