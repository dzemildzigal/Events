package com.lambda.EventService.models;

import com.sun.istack.NotNull;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long eventId;

    @NotNull
    private String eventName;
    @NotNull
    private String description;
    //veze
    @MapsId //šta se mapira
    @ManyToOne(fetch= FetchType.LAZY)  //tip veze
    @JoinColumn(name = "locationId") //kako se zove kolona u tabeli na koju se veže
    private Location location; //instanca klase na koju se veže


    //veze
    @MapsId //šta se mapira
    @ManyToOne(fetch= FetchType.LAZY)  //tip veze
    @JoinColumn(name = "eventTypeId") //kako se zove kolona u tabeli na koju se veže
    private EventType eventType; //instanca klase na koju se veže


    @NotNull
    private Boolean canBuyTicket;
    @NotNull
    private Double ticketPrice;
    @NotNull
    private Long numberOfTicketsAvailable;
    @NotNull
    private String eventPictureURL;
    @NotNull
    private Long createdByUserId;
    //veze
    @MapsId //šta se mapira
    @ManyToOne(fetch= FetchType.LAZY)  //tip veze
    @JoinColumn(name = "eventStatusId") //kako se zove kolona u tabeli na koju se veže
    private EnuEventStatus enuEventStatus; //instanca klase na koju se veže
    @NotNull
    private Date eventTime;

}
