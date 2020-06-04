package com.lambda.EventService.Helpers;
import com.lambda.EventService.Models.Entity.Event;
import com.lambda.EventService.Models.Api.MessageDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationServiceHelper {
    @Autowired
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private String username = "test", password="testtest";
    public boolean notifyUsersOfEventCreation(@NotNull Event newCreatedEvent)throws Exception{
        httpHeaders.clear();
        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        Map<String,String> info = new HashMap<String,String>();
        info.put("eventTypeId", newCreatedEvent.getEventType().getEventTypeId().toString());
        info.put("description",newCreatedEvent.getEventName() + " will take place at the location "+ newCreatedEvent.getLocation().getDescription()
                + " and begin at the time of " + newCreatedEvent.getEventTime().toString());
        HttpEntity<Map<String,String>> entity = new HttpEntity<>(info,httpHeaders);

            ResponseEntity<MessageDTO> rez = restTemplate.exchange("http://NotificationService/notifications/notify-users-of-event-creation",
                                                                    HttpMethod.POST,
                                                                    entity,
                                                                    MessageDTO.class);
            return rez.getStatusCode().is2xxSuccessful();

    }



}
