package com.lambda.EventService.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table
public class EventComments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventCommentId;
    private Long userId;


    @ManyToOne
    @JsonIgnore
    public Event event;

    @javax.validation.constraints.NotNull(message = "EventComment text can not be null, must be of type String!")
    private String text;
}
