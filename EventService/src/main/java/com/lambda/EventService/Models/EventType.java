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
@Table
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventTypeId;
    @javax.validation.constraints.NotNull(message = "eventTypeDescription can not be null, must be of type String!")
    private String eventTypeDescription;

    @OneToMany(mappedBy = "eventType")
    @JsonIgnoreProperties("eventList")
    private List<Event> eventList;
}
