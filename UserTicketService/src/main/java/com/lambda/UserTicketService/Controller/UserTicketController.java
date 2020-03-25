package com.lambda.UserTicketService.Controller;


import com.lambda.UserTicketService.Service.IUserTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usertickets")
public class UserTicketController {
    @Autowired
    IUserTicketService userTicketService;

}
