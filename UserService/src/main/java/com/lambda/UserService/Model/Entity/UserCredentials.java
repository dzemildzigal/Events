package com.lambda.UserService.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userCredentialsId;

    @NotNull(message  = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    @Length(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String username;


    @NotNull(message  = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;


    @MapsId
    @OneToOne
    @JoinColumn(name = "userId")
    @NotNull(message = "UserInfo cannot be null")
    private UserInfo user;

}
