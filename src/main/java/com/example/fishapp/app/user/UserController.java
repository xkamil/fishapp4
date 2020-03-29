package com.example.fishapp.app.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import com.example.fishapp.app.user.dto.UserCreateCommandDto;
import com.example.fishapp.app.user.model.User;
import com.example.fishapp.app.user.repository.UserRepository;
import com.example.fishapp.app.user.repository.UserQueryRepository;
import com.example.fishapp.app.user.service.UserService;
import com.example.fishapp.app.user.view.UserView;
import com.example.fishapp.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserQueryRepository userQueryRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserQueryRepository userQueryRepository, UserService userService) {
        this.userQueryRepository = userQueryRepository;
        this.userService = userService;
    }

    @PutMapping
    private User getOrCreate(@RequestBody @Valid UserCreateCommandDto userCreateCommandDto) {
        return userService.getOrCreate(userCreateCommandDto);
    }

    @GetMapping("/me")
    private User get(@RequestAttribute @NotNull User user) {
        return user;
    }

    @GetMapping("/{id}")
    public UserView findById(@PathVariable("id") UUID id) {
        return userQueryRepository.findByUserId(id).orElseThrow(NotFoundException::new);
    }
}
