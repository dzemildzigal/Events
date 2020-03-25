package com.lambda.UserTicketService.Controller;


import com.lambda.UserTicketService.Service.IUserTicketService;
import com.lambda.UserTicketService.model.CCPayment;
import com.lambda.UserTicketService.model.UserTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usertickets")
public class UserTicketController {
    @Autowired
    IUserTicketService userTicketService;

    @GetMapping("/deleteticket/{id}")
    public void deleteUserTicket(@PathVariable Long id){
        userTicketService.deleteUserTicket(id);
    }

    @GetMapping("/{id}")
    public UserTicket getUserTicketInfo(@PathVariable Long id){
        return this.userTicketService.getUserTicketById(id);
    }

    @PostMapping("/newticket")
    public UserTicket createNewTicket(@RequestBody UserTicket userTicket){
        return this.userTicketService.createUserTicket(userTicket);
    }

    @PostMapping("/newpayment")
    public CCPayment createNewPayment(@RequestBody CCPayment ccPayment)
    {
        return this.userTicketService.createPaymentForTicket(ccPayment);
    }

    @GetMapping("users-tickets/{userId}")
    public List<UserTicket> getUserTicketsByUserId(long userId) {
        return this.userTicketService.getUserTicketsByUserId(userId);
    }
}
