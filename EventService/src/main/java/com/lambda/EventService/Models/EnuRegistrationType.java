package com.lambda.EventService.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

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
    @NotNull
    private String description;

    @OneToMany(mappedBy = "enuRegistrationType")
    @JsonIgnoreProperties("userEventRegistrationList")
    private List<UserEventRegistration> userEventRegistrationList;

}
