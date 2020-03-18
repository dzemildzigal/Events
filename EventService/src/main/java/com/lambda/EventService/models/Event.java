package com.lambda.EventService.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    private String eventName;
    private String description;
    private Long locationId;
    private Long eventTypeId;
    private Boolean canBuyTicket;
    private Double ticketPrice;
    private Long numberOfTicketsAvailable;
    private String eventPictureURL;
    private Long createdByUserId;
    private Long eventStatusId;
    private Date eventTime;
// ostali atributi ...

}
