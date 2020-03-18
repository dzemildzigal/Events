package com.lambda.EventService.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class EventComments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventCommentId;
    private Long userId;
    private Long eventId;
    private String text;
}
