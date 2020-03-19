package com.lambda.EventService.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserEventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userEventRegistrationId;
    private Long userId;
    //veze
    @MapsId //šta se mapira
    @ManyToOne(fetch= FetchType.LAZY)  //tip veze
    @JoinColumn(name = "registrationTypeId") //kako se zove kolona u tabeli na koju se veže
    private EnuRegistrationType enuRegistrationType; //instanca klase na koju se veže

    //veze
    @MapsId //šta se mapira
    @ManyToOne(fetch= FetchType.LAZY) //tip veze
    @JoinColumn(name = "eventId") //kako se zove kolona u tabeli na koju se veže
    private Event event; //instanca klase na koju se veže
}
