package com.lambda.UserTicketService.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userTicketId;

    @NotNull
    private Long userId;

    @NotNull
    private Long eventId;

}
