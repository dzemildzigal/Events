package com.lambda.EventService.models;

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
public class UserEventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userEventRegistrationId;
    private int userId;
    private int registrationTypeId;
    private int eventId;
}
