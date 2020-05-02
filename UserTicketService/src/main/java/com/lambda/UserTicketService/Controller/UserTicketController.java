package com.lambda.UserTicketService.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.lambda.UserTicketService.Grpc.GRPCUserTicketServiceClient;
import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.Model.CCPayment;
import com.lambda.UserTicketService.Model.UserTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("usertickets")
public class UserTicketController {

    @Autowired
    IUserTicketService userTicketService;

    @Autowired
    GRPCUserTicketServiceClient grpcUserTicketServiceClient;
    @GetMapping("/{id}")
    public UserTicket getUserTicketInfo(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationToken) throws AccessDeniedException {
        grpcUserTicketServiceClient.createSystemEvent(null);
        return this.userTicketService.getUserTicketById(id, authorizationToken);
    }

    /*
    -- not used in the application
    @GetMapping("/event-tickets/{eventid}")
    public List<UserTicket> getUserTicketsByEventId(@PathVariable long eventid) {
        return this.userTicketService.getUserTicketsByEventId(eventid);
    }*/


    @PostMapping("/payment/create")
    public CCPayment createNewPayment(@RequestBody CCPayment ccPayment,  @RequestHeader(value = "Authorization") String authorizationToken) throws AccessDeniedException, JsonProcessingException {
        return this.userTicketService.createPaymentForTicket(ccPayment, authorizationToken);
    }

    @GetMapping("/users-tickets/{userId}")
    public List<UserTicket> getUserTicketsByUserId(@PathVariable  long userId, @RequestHeader(value = "Authorization") String authorizationToken ) throws AccessDeniedException {
        return this.userTicketService.getUserTicketsByUserId(userId, authorizationToken);
    }
}
