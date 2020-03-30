package com.example.fishapp.app.user.model;

import com.example.fishapp.app.user.dto.UserCreateDto;
import com.example.fishapp.model.Fish;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private Role role;

    @Email
    private String email;

    private String facebookId;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Fish> fish = new HashSet<>();

    public static User from(UserCreateDto userCreateDto) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setRole(Role.USER);
        user.setEmail(userCreateDto.getEmail());
        user.setFacebookId(userCreateDto.getFacebookId());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        return user;
    }
}
