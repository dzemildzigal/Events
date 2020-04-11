package com.lambda.EventService.Models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class EnuRegistrationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enuRegistrationTypeId;
    @NotNull(message = "Description of EnuRegistrationType can not be null, and must be of existing types!")
    private String description;

    @OneToMany(mappedBy = "enuRegistrationType")
    @JsonIgnoreProperties("userEventRegistrationList")
    private List<UserEventRegistration> userEventRegistrationList;

}
