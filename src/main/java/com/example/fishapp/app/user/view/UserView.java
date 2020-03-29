package com.example.fishapp.app.user.view;

import java.util.UUID;
import com.example.fishapp.app.user.model.Role;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserView {

    private UUID id;

    private String firstName;

    private String lastName;

    private Role role;

    private String email;

    private String facebookId;

    @QueryProjection
    public UserView(UUID id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }


}
