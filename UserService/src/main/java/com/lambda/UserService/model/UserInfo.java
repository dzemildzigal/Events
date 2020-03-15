package com.lambda.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;
    private String email;
    private String fullName;
    private String profilePictureURL;
}

