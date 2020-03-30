package com.example.fishapp.app.user.service;

import java.util.Optional;
import com.example.fishapp.app.user.dto.UserCreateDto;
import com.example.fishapp.app.user.model.User;
import com.example.fishapp.app.user.repository.UserRepository;
import com.example.fishapp.app.user.repository.UserQueryRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, UserQueryRepository userQueryRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreate(UserCreateDto userCreateDto) {
        Optional<User> user = userRepository.findByEmail(userCreateDto.getEmail());

        return user.orElseGet(() -> {
            User newUser = User.from(userCreateDto);
            return userRepository.save(newUser);
        });
    }
}
