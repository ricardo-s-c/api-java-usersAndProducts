package com.apijavalogin.apijavalogin.controllers.excepitions;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {
    
    private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
