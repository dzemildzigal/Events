package com.lambda.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


public class UserCredentials {

    private int userCredentialsId;
    private String username;
    private String password;
}
