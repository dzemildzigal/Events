package com.lambda.UserService.model.entity;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;

    @Length(min = 3, max = 40, message = "Email length must be between 3 and 40 characters")
    @NotNull(message  = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Length(min = 3, max = 40, message = "Name length must be between 3 and 40 characters")
    @NotNull(message  = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String fullName;

    @Nullable
    private String profilePictureURL;
}

