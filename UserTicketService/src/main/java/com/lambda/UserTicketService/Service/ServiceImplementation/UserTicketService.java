package com.lambda.UserTicketService.Service.ServiceImplementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lambda.UserTicketService.Helpers.ICommunicationHelper;
import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.Model.CCPayment;
import com.lambda.UserTicketService.Model.UserTicket;
import com.lambda.UserTicketService.Repository.ICCPaymentRepository;
import com.lambda.UserTicketService.Repository.IUserTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.List;

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
    public UserTicket getUserTicketById(long userTicketId, String authorizationToken) throws AccessDeniedException {
        UserTicket userTicket = this.userTicketRepository.findById(userTicketId).orElseThrow(EntityNotFoundException::new);
        if (this.communicationHelper.checkIsUserAuthorized(Long.toString(userTicket.getUserId()), authorizationToken)) {
            return userTicket;
        }
        throw new AccessDeniedException("User is not authorized");
    }

    @Override
    public List<UserTicket> getUserTicketsByEventId(long eventId)
    {
        return this.userTicketRepository.findByEventId(eventId);
    }

    @Override
    public List<UserTicket> getUserTicketsByUserId(long userId, String authorizationToken) throws AccessDeniedException {
        if (this.communicationHelper.checkIsUserAuthorized(Long.toString(userId), authorizationToken)) {
            return this.userTicketRepository.findByUserId(userId);
        }
        throw new AccessDeniedException("User is not authorized");
    }

    @Override
    public CCPayment createPaymentForTicket(CCPayment ccPayment, String authorizationToken) throws AccessDeniedException, JsonProcessingException {
        if (this.communicationHelper.buyATicket(ccPayment.getUserTicket().getEventId(), ccPayment.getUserTicket().getUserId(), authorizationToken)) {
            return ccPaymentRepository.save(ccPayment);
        }
        throw new AccessDeniedException("User is not authorized");
    }
}
