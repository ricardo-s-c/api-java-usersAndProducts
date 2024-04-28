package com.apijavalogin.apijavalogin.dto;

import com.apijavalogin.apijavalogin.entities.User;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;

    public UserDTO (User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
    }
}
