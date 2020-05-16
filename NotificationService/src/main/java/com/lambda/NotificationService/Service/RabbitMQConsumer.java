package com.lambda.NotificationService.Service;

import com.lambda.NotificationService.Grpc.GRPCNotificationServiceClient;
import com.lambda.NotificationService.Helpers.UserServiceHelper;
import com.lambda.NotificationService.Service.INotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.NotificationService.Model.Api.CreatedNotificationDTO;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
@Service
@NoArgsConstructor
public class RabbitMQConsumer implements MessageListener {

    public INotificationService notificationservice;
    public RabbitMQConsumer(INotificationService i){
        this.notificationservice = i;
    }
    @Override
    public void onMessage(Message message) {
        try {
            String json = new String(message.getBody());
            ObjectMapper objectMapper = new ObjectMapper();
            CreatedNotificationDTO notification = objectMapper.readValue(json,CreatedNotificationDTO.class);
            String msg = notification.getDescription();
            this.notificationservice.notifyUsersOfCreation(notification.getEventTypeId(), notification.getDescription());
            System.out.println(notification.getDescription());
        } catch (Exception e) {

        }
    }
}
