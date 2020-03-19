package com.lambda.EventService.models;

import com.sun.istack.NotNull;
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
public class EnuRegistrationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enuRegistrationTypeId;
    @NotNull
    private String description;



}
