package com.lambda.UserTicketService.repository;

import com.lambda.UserTicketService.model.CCPayment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICCPaymentRepository extends CrudRepository<CCPayment, Long> {
    List<CCPayment> findByUserTicketUserId (long userId);
}
