package com.lambda.EventService.Models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    @NotNull
    private String eventName;
    @NotNull
    private String description;
    //veze

    @ManyToOne
    @JsonIgnore
    public Location location;


    //veze
    @ManyToOne
    @JsonIgnore
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
    @ManyToOne//(fetch= FetchType.LAZY, cascade = CascadeType.ALL)  //tip veze
    @JsonIgnore
    private EnuEventStatus enuEventStatus; //instanca klase na koju se veže
    @NotNull
    private Date eventTime;

    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties("eventCommentsList")
    private List<EventComments> eventCommentsList;

    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties("userEventRegistrationList")
    private List<UserEventRegistration> userEventRegistrationList;

}
