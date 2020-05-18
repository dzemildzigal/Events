package com.lambda.EventService.Models.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambda.EventService.Models.Entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import com.lambda.EventService.Helpers.IDGenerator;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class EventType {

    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @GenericGenerator(
            name = "id_gen",
            strategy = "com.lambda.EventService.Helpers.IDGenerator",
            parameters = {
                    @Parameter(name = IDGenerator.INCREMENT_PARAM, value = 1),
                    @Parameter(name = IDGenerator.VALUE_PREFIX_PARAMETER, value = 0),
                    @Parameter(name = IDGenerator.NUMBER_FORMAT_PARAMETER, value = 0) })*/
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventTypeId;
    @javax.validation.constraints.NotNull(message = "eventTypeDescription can not be null, must be of type String!")
    public String eventTypeDescription;

    @OneToMany(mappedBy = "eventType")
    @JsonIgnoreProperties("eventList")
    private List<Event> eventList;
}
