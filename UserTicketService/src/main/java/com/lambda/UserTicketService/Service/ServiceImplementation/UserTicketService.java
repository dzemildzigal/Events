package com.lambda.UserTicketService.Service.ServiceImplementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lambda.UserTicketService.Helpers.CommunicationHelper;
import com.lambda.UserTicketService.Helpers.ICommunicationHelper;
import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;
import com.lambda.UserTicketService.repository.ICCPaymentRepository;
import com.lambda.UserTicketService.repository.IUserTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class UserTicketService implements IUserTicketService {

    @Autowired
    ICCPaymentRepository ccPaymentRepository;

    @Autowired
    IUserTicketRepository userTicketRepository;

    @Autowired
    ICommunicationHelper communicationHelper;

    @Override
    public UserTicket createUserTicket(UserTicket userTicket){
        return userTicketRepository.save(userTicket);
    }

    @Override
    public UserTicket getUserTicketById(long id) {
        return this.userTicketRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public List<UserTicket> getUserTicketsByEventId(long eventid)
    {
        return this.userTicketRepository.findByEventId(eventid);
    }

    @Override
    public List<UserTicket> getUserTicketsByUserId(long id) {
        return this.userTicketRepository.findByUserId(id);
    }

    @Override
    public CCPayment createPaymentForTicket(CCPayment ccPayment, String authorizationToken) throws AccessDeniedException, JsonProcessingException {
        if (this.communicationHelper.buyATicket(ccPayment.getUserTicket().getEventId(), ccPayment.getUserTicket().getUserId(), authorizationToken)) {
            return ccPaymentRepository.save(ccPayment);
        }
        throw new AccessDeniedException("User is not authorized");
    }
}
