package com.lambda.EventService.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    private String description;

    @OneToMany(mappedBy = "location")
    @JsonIgnoreProperties("eventList")
    private List<Event> eventList = new ArrayList<>();

    public Location(Long locId, String s) {
        locationId = locId;
        description = s;
    }
}
