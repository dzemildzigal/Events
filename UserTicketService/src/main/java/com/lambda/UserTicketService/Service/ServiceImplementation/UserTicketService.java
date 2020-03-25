package com.lambda.UserTicketService.Service.ServiceImplementation;

import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;
import com.lambda.UserTicketService.repository.ICCPaymentRepository;
import com.lambda.UserTicketService.repository.IUserTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserTicketService implements IUserTicketService {

    @Autowired
    ICCPaymentRepository ccPaymentRepository;
    @Autowired
    IUserTicketRepository userTicketRepository;

    @Override
    public UserTicket createUserTicket(UserTicket userTicket){
        return userTicketRepository.save(userTicket);
    }
     @Override
     public void deleteUserTicket(Long id){
         userTicketRepository.deleteById(id);
     }

    @Override
    public UserTicket getUserTicketById(long id) {
        return this.userTicketRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<UserTicket> getUserTicketsByUserId(long id) {
        return this.userTicketRepository.findByUserId(id);
    }

    @Override
    public CCPayment createPaymentForTicket(CCPayment ccPayment) {
        return ccPaymentRepository.save(ccPayment);
    }
}
