package com.themaj.smart_books.controller;

import com.themaj.smart_books.dto.LoginRequest;
import com.themaj.smart_books.model.User;
import com.themaj.smart_books.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{username}")
    public Optional<User> fetchUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.register(user);
    }
    @PostMapping("login")
    public String login(@RequestBody LoginRequest request) {

        return userService.login(request.username(), request.password());
    }
}
