package com.lambda.EventService.Helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.EventService.Models.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationServiceHelper {
    @Autowired
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();

    public boolean notifyUsersOfEventCreation(Event newCreatedEvent)throws Exception{
        httpHeaders.clear();
        httpHeaders.add("Content-Type","application/json");
        HttpEntity entity = new HttpEntity(httpHeaders);
        ObjectMapper payload = new ObjectMapper();
        Map<String,String> info = new HashMap<String,String>();
        info.put("eventTypeId", newCreatedEvent.getEventType().getEventTypeId().toString());
        info.put("description",newCreatedEvent.getLocation().getDescription()+" will take place at the location "+ newCreatedEvent.getLocation().getDescription()+" and begin at the time of "+newCreatedEvent.getEventTime().toString());
        payload.writeValueAsString(info);
        ResponseEntity<Void> rez = restTemplate.exchange("http://NotificationService/notifications/notify-users-of-event-creation",HttpMethod.POST,entity,Void.class,payload);
        return rez.getStatusCode().is2xxSuccessful();
    }



}
