package com.lambda.EventService.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@Entity
public class UserEventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userEventRegistrationId;
    private Long userId;
    //veze
    //@MapsId //šta se mapira
    @ManyToOne//(fetch= FetchType.LAZY)  //tip veze
    @JsonIgnore
    private EnuRegistrationType enuRegistrationType; //instanca klase na koju se veže

    //veze
    //@MapsId //šta se mapira
    @ManyToOne//(fetch= FetchType.LAZY) //tip veze
    @JsonIgnore
    private Event event; //instanca klase na koju se veže
}
