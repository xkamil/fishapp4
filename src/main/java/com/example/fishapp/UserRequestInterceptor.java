package com.example.fishapp;

import com.example.fishapp.app.user.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class UserRequestInterceptor extends HandlerInterceptorAdapter {

    private final UserRepository userRepository;

    public UserRequestInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader("userId") != null) {
            UUID userId = UUID.fromString(request.getHeader("userId"));
            userRepository.findById(userId).ifPresent(user -> request.setAttribute("user", user));
        }
        return true;
    }
}
