package com.lambda.NotificationService.Model.Api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatedNotificationDTO implements Serializable {

    //@NotNull(message = "eventTypeId cannot be null")
    private Long eventTypeId;
    //@NotNull(message = "description cannot be null")
    private String description;
}
