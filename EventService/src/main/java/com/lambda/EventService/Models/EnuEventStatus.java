package com.lambda.EventService.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @NotNull
    private String description;
    @OneToMany(mappedBy = "enuEventStatus")
    @JsonIgnoreProperties("events")
    private List<Event> events;

}
