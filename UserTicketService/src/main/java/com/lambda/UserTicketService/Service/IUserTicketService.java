package com.lambda.UserTicketService.Service;

import com.lambda.UserTicketService.model.CCPayment;

public interface IUserTicketService {
    CCPayment createPaymentForTicket(CCPayment ccPayment);
}
