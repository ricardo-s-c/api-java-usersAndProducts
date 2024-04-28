package com.apijavalogin.apijavalogin.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apijavalogin.apijavalogin.config.security.TokenService;
import com.apijavalogin.apijavalogin.dto.LoginRequestDTO;
import com.apijavalogin.apijavalogin.dto.RegisterRequestDTO;
import com.apijavalogin.apijavalogin.dto.ResponseDTO;
import com.apijavalogin.apijavalogin.entities.User;
import com.apijavalogin.apijavalogin.repositories.UserRepository;
import com.apijavalogin.apijavalogin.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        var userToken = service.login(body);

        return ResponseEntity.ok(userToken);
    }


    @PostMapping("/users")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        var userToken = service.register(body);
        if (userToken.user() == null || userToken.accessToken() == null) {
            return ResponseEntity.badRequest().body("User já existe!");
        }
        return ResponseEntity.ok(userToken); 
    }
}
