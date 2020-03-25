package com.lambda.UserTicketService.Service.ServiceImplementation;

import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;
import com.lambda.UserTicketService.repository.ICCPaymentRepository;
import com.lambda.UserTicketService.repository.IUserTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTicketService implements IUserTicketService {

    @Autowired
    ICCPaymentRepository ccPaymentRepository;
    IUserTicketRepository iUserTicketRepository;
   /* @Override
    public UserTicket findById(long id) {
            return iUserTicketRepository.findById(id);

    }*/
    @Override
    public UserTicket createUserTicket(UserTicket userTicket){
        return iUserTicketRepository.save(userTicket);
    }
     @Override
     public void deleteUserTicket(Long id){
        iUserTicketRepository.deleteById(id);
     }
    @Override
    public CCPayment createPaymentForTicket(CCPayment ccPayment) {
        return ccPaymentRepository.save(ccPayment);
    }
}
