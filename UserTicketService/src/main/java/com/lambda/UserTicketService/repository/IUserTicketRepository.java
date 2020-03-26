package com.lambda.UserTicketService.repository;

import com.lambda.UserTicketService.model.UserTicket;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserTicketRepository extends CrudRepository<UserTicket, Long> {
        List<UserTicket> findByUserId(Long id);
        List<UserTicket> findByEventId(long eventid);
}
