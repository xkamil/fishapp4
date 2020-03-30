package com.example.fishapp.app.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserCreateDto {

    private String firstName;

    private String lastName;

    @Email
    @NotEmpty
    private String email;

    private String facebookId;
}
