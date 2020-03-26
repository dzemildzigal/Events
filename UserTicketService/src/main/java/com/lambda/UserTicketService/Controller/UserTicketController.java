package com.lambda.UserTicketService.Controller;


import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usertickets")
public class UserTicketController {
    @Autowired
    IUserTicketService userTicketService;

    @GetMapping("/{id}")
    public UserTicket getUserTicketInfo(@PathVariable Long id) {
        return this.userTicketService.getUserTicketById(id);
    }

    @PostMapping("/event-tickets/{eventid}")
    public List<UserTicket> getUserTicketsByEventId(@PathVariable long eventid){
        return this.userTicketService.getUserTicketsByEventId(eventid);
    }


    @PostMapping("/payment/create")
    public CCPayment createNewPayment(@RequestBody CCPayment ccPayment) {
        return this.userTicketService.createPaymentForTicket(ccPayment);
    }

    @GetMapping("users-tickets/{userId}")
    public List<UserTicket> getUserTicketsByUserId(@PathVariable  long userId) {
        return this.userTicketService.getUserTicketsByUserId(userId);
    }
}
