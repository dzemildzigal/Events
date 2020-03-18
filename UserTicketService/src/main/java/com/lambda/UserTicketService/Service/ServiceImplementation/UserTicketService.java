package com.lambda.UserTicketService.Service.ServiceImplementation;

import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;
import com.lambda.UserTicketService.repository.ICCPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTicketService implements IUserTicketService {

    @Autowired
    ICCPaymentRepository ccPaymentRepository;


    public CCPayment createPaymentForTicket(CCPayment ccPayment) {
        return ccPaymentRepository.save(ccPayment);
    }
}
