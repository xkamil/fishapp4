package com.example.fishapp.controller;

import com.example.fishapp.dto.UserCreateDto;
import com.example.fishapp.repository.UserRepository;
import com.example.fishapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping
    private User getOrCreate(@RequestBody @Valid UserCreateDto userCreateDto) {
        Optional<User> user = userRepository.findByEmail(userCreateDto.getEmail());

        return user.orElseGet(() -> {
            log.debug("Creating new user: " + userCreateDto);
            User newUser = User.from(userCreateDto);
            return userRepository.save(newUser);
        });
    }

    @GetMapping("/me")
    private User get(@RequestAttribute @NotNull User user) {
        return user;
    }
}
