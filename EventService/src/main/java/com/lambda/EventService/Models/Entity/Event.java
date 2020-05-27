package com.lambda.EventService.Models.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @javax.validation.constraints.NotNull(message = "Event name can not be null, must be of type String!")
    private String eventName;
    @javax.validation.constraints.NotNull(message = "Event description can not be null, must be of type String!")
    private String description;

    @ManyToOne
    @JsonIgnore
    public Location location;

    @ManyToOne
    @JsonIgnore
    public EventType eventType;


    @javax.validation.constraints.NotNull(message = "canBuyTicket can not be null, must be of type Boolean!")
    private Boolean canBuyTicket;
    //@javax.validation.constraints.NotNull(message = "ticketPrice can not be null, must be of type Double!")
    private Double ticketPrice;
    //@javax.validation.constraints.NotNull(message = "numberOfTicketsAvailable can not be null, must be of type Long!")
    private Long numberOfTicketsAvailable;
    @javax.validation.constraints.NotNull(message = "eventPictureURL can not be null, must be of type String!")
    private String eventPictureURL;
    @javax.validation.constraints.NotNull(message = "createdByUserId can not be null, must be of type Lonng!")
    private Long createdByUserId;

    @ManyToOne
    @JsonIgnore
    private EnuEventStatus enuEventStatus;
    @javax.validation.constraints.NotNull(message = "ticketPrice can not be null, must be of type SQL date, YYYY-MM-DD!")
    public  Date eventTime;

    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties("eventCommentsList")
    private List<EventComments> eventCommentsList;

    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties("userEventRegistrationList")
        private List<UserEventRegistration> userEventRegistrationList;

}
