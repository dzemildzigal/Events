package com.lambda.UserService.model;

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
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userCredentialsId;
    @NotNull
    @Length(min = 3, max = 20)
    private String username;
    @NotNull
    @Length(min = 3, max = 100)
    private String password;


    @MapsId
    @OneToOne
    @JoinColumn(name = "userId")
    private UserInfo user;


}
