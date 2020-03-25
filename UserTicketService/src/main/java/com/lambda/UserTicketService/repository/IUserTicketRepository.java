package com.lambda.UserTicketService.repository;

import com.lambda.UserTicketService.model.UserTicket;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserTicketRepository extends CrudRepository<UserTicket, Long> {
//UserTicket findById(Long id);
void deleteById(Long id);

}
