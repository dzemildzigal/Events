package com.lambda.EventService.models;


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
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventTypeId;
    private String description;
}
