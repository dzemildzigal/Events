package com.lambda.UserTicketService.Service;

import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;

import java.util.List;
import java.util.Optional;

public interface IUserTicketService {

    UserTicket getUserTicketById(long id);
    CCPayment createPaymentForTicket(CCPayment ccPayment);
    UserTicket createUserTicket(UserTicket userTicket);
    void deleteUserTicket(Long id);
    List<UserTicket> getUserTicketsByUserId(long id);

}
