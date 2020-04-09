package com.lambda.EventService.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@NoArgsConstructor
public class EventWrapper {
    public Event event;
    public Location location;
    public EnuEventStatus enuEventStatus;
    public EventType eventType;
    public Long userId;
}
