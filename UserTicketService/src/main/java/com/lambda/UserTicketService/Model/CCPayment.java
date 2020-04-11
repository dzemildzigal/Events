package com.lambda.UserTicketService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CCPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long paymentId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "userTicketId")
    @NotNull(message = "UserTicket cannot be null")
    private UserTicket userTicket;

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    @NotNull(message = "CC number cannot be null")
    @NotBlank(message = "CC number cannot be blank")
    @Length(min = 9, message = "CC number length must be greater than 9")
    private String creditCardNumber;
}
