package com.lambda.UserTicketService.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private UserTicket userTicket;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String creditCardNumber;
}
