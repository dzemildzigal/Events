package com.lambda.UserTicketService.Service;

import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;

import java.util.Optional;

public interface IUserTicketService {
 //UserTicket findById(long id);

    CCPayment createPaymentForTicket(CCPayment ccPayment);
    UserTicket createUserTicket(UserTicket userTicket);
    void deleteUserTicket(Long id);


}
