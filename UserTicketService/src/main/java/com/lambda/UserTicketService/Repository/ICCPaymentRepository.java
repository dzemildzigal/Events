package com.lambda.UserTicketService.Repository;

import com.lambda.UserTicketService.Model.CCPayment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICCPaymentRepository extends CrudRepository<CCPayment, Long> {
    List<CCPayment> findByUserTicketUserId (long userId);
}
