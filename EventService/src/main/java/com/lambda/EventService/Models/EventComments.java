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

   //veze
    //@MapsId //šta se mapira
    @ManyToOne//(fetch= FetchType.LAZY)  //tip veze
    @JsonIgnore
    public Event event; //instanca klase na koju se veže

    private String text;
}
