package com.example.SystemEvents.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SystemEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long systemEventId;
    private LocalDateTime timeStamp;
    private String serviceName;
    private String userAuthToken;
    private String actionType;
    private String resourceObject;
    private String actionResult;
}
