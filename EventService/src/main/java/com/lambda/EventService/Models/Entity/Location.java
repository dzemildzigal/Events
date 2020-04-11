package com.lambda.EventService.Models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lambda.EventService.Models.Entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationId;
    @javax.validation.constraints.NotNull(message = "Location description can not be null, must be of type String!")
    private String description;

    @OneToMany(mappedBy = "location")
    @JsonIgnoreProperties("eventList")
    private List<Event> eventList = new ArrayList<>();

    public Location(Long locId, String s) {
        locationId = locId;
        description = s;
    }
}
