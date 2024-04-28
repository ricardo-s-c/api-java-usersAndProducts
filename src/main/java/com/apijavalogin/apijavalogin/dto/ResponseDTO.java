package com.apijavalogin.apijavalogin.dto;

import com.apijavalogin.apijavalogin.entities.User;

public record ResponseDTO (User user, String accessToken) { }
