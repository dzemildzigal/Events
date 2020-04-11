package com.lambda.UserTicketService.Repository;

import com.lambda.UserTicketService.Model.UserTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserTicketRepository extends CrudRepository<UserTicket, Long> {
        List<UserTicket> findByUserId(Long id);
        List<UserTicket> findByEventId(long eventid);
}
