package com.lambda.NotificationService.Model.Entity;

//import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class UserSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userSubscriptionId;

    @NotNull(message  = "userId cannot be null")
    private Long userId;

    @NotNull(message  = "eventTypeId cannot be null")
    private Long eventTypeId;



}
