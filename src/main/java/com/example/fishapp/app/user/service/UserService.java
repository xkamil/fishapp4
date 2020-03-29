package com.example.fishapp.app.user.service;

import java.util.Optional;
import com.example.fishapp.app.user.dto.UserCreateCommandDto;
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

    public User getOrCreate(UserCreateCommandDto userCreateCommandDto) {
        Optional<User> user = userRepository.findByEmail(userCreateCommandDto.getEmail());

        return user.orElseGet(() -> {
            User newUser = User.from(userCreateCommandDto);
            return userRepository.save(newUser);
        });
    }
}
