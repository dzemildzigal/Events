package com.lambda.UserTicketService.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userTicketId;

    @NotNull(message = "UserId cannot be null")
    private Long userId;

    @NotNull(message = "eventId cannot be null")
    private Long eventId;

}
