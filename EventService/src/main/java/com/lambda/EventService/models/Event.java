package com.lambda.EventService.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventId;
    private String eventName;
    private String description;
    private int locationId;
    private int eventTypeId;
    private boolean canBuyTicket;
    private double ticketPrice;
    private int numberOfTicketsAvailable;
    private String eventPictureURL;
    private int createdByUserId;
    private int eventStatusId;
    private Date eventTime;
// ostali atributi ...

}
