package com.lambda.NotificationService.Model.Entity;

//import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;
    @NotNull(message = "userId cannot be null")
    private Long userId;
    @NotNull(message = "description cannot be null")
    @Length(min = 3, max = 200,message="Description length must be between 3 and 200 characters!")
    private String description;
    @NotNull(message = "isSeen cannot be null")
    private boolean isSeen;


}
