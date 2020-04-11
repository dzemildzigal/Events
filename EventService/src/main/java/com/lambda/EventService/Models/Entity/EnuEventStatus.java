package com.lambda.EventService.Models.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EnuEventStatus")
public class EnuEventStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventStatusId;
    @NotNull(message = "Description of EnuEventStatus can not be null!")
    private String description;
    @OneToMany(mappedBy = "enuEventStatus")
    @JsonIgnoreProperties("events")
    private List<Event> events;

}
