package com.lambda.EventService.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationId;
    private String description;

    @OneToMany(mappedBy = "location")
    private List<Event> eventList;

    public Location(Long locId, String s) {
        locationId = locId;
        description = s;
    }
}
