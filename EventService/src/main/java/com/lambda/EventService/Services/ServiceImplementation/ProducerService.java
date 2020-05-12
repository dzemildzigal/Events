package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.Models.Api.CreatedNotificationDTO;
import com.lambda.EventService.Models.Entity.Event;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${rmq.exchange}")
    String exchange;

    @Value("${rmq.routingKey}")
    private String routingKey;

    public void send(Event newCreatedEvent ) {
        Long eventTypeId = newCreatedEvent.getEventType().getEventTypeId();
        String description = newCreatedEvent.getLocation().getDescription()+" will take place at the location "+ newCreatedEvent.getLocation().getDescription()+" and begin at the time of "+newCreatedEvent.getEventTime().toString();
        CreatedNotificationDTO createdNotificationDTO = new CreatedNotificationDTO(eventTypeId, description);
        rabbitTemplate.convertAndSend(exchange, routingKey, createdNotificationDTO);
    }
}
