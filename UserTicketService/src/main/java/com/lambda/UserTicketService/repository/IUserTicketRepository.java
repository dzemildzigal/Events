package com.lambda.UserTicketService.repository;

import com.lambda.UserTicketService.model.UserTicket;
import org.springframework.data.repository.CrudRepository;

public interface IUserTicketRepository extends CrudRepository<UserTicket, Long> {
}
