package com.lambda.UserTicketService.repository;

import com.lambda.UserTicketService.model.CCPayment;
import org.springframework.data.repository.CrudRepository;

public interface ICCPaymentRepository extends CrudRepository<CCPayment, Long> {

}
