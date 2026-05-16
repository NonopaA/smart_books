package com.themaj.smart_books.service;

import com.themaj.smart_books.model.*;
import com.themaj.smart_books.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder passwordEncoderr;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Optional<User>  getUserByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
        boolean matches = passwordEncoder.matches(
                password,
                user.getPassword());
        if (matches) {
            //return "Login successful";
            return jwtService.generateToken(username);
        }
        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Invalid password");
    }
}
