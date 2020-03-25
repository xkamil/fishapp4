package com.example.fishapp.model;

import com.example.fishapp.dto.UserCreateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Column(nullable = true)
    private Role role;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
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
