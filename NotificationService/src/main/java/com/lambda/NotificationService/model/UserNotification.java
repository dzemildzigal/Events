package com.lambda.NotificationService.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;
    @NotNull
    private Long userId;
    @NotNull
    @Length(min = 3, max = 200)
    private String description;
    @NotNull
    private boolean isSeen;


}
