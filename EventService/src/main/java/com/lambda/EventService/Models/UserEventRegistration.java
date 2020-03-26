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
    @javax.validation.constraints.NotNull(message = "UserEventRegistration userId can not be null, must be of type Long!")
    private Long userId;

    @ManyToOne
    @JsonIgnore
    private EnuRegistrationType enuRegistrationType;


    @ManyToOne
    @JsonIgnore
    private Event event;
}
