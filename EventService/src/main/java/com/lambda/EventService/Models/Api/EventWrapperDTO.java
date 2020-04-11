package com.lambda.EventService.Models.Api;

import com.lambda.EventService.Models.Entity.EnuEventStatus;
import com.lambda.EventService.Models.Entity.Event;
import com.lambda.EventService.Models.Entity.EventType;
import com.lambda.EventService.Models.Entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventWrapperDTO {
    public Event event;
    public Location location;
    public EnuEventStatus enuEventStatus;
    public EventType eventType;
    public Long userId;
}
