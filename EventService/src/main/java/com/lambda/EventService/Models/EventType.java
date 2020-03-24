package com.lambda.EventService.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String description;

    @OneToMany(mappedBy = "eventType")
    @JsonIgnoreProperties("eventList")
    private List<Event> eventList;
}
