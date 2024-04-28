package com.apijavalogin.apijavalogin.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apijavalogin.apijavalogin.config.security.TokenService;
import com.apijavalogin.apijavalogin.dto.LoginRequestDTO;
import com.apijavalogin.apijavalogin.dto.RegisterRequestDTO;
import com.apijavalogin.apijavalogin.dto.ResponseDTO;
import com.apijavalogin.apijavalogin.dto.UserDTO;
import com.apijavalogin.apijavalogin.entities.User;
import com.apijavalogin.apijavalogin.repositories.UserRepository;
import com.apijavalogin.apijavalogin.services.excepitions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public Map<String, Object> login(LoginRequestDTO body) {
        try {
            User user = repository.findByEmail(body.email()).get();
            if(passwordEncoder.matches(body.password(), user.getPassword())) {
    
            String accessToken = this.tokenService.generateToken(user);
    
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("accessToken", accessToken);
            return response;
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("error", new ResourceNotFoundException("Entity not found"));
        return response;
    }

    public ResponseDTO register(RegisterRequestDTO body) {
        Optional<User> user = repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            repository.save(newUser);

            String accessToken = this.tokenService.generateToken(newUser);
            return new ResponseDTO(newUser, accessToken);
        }
       return new ResponseDTO(null, null);
    }

    public UserDTO findById(Long id) {
        Optional<User> optional = repository.findById(id);
        User entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }
}
