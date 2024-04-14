package com.apijavalogin.apijavalogin.controllers.excepitions;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthCustomError {

    private String error;
	
	@JsonProperty("error_description")//renomeia o campo no Json
	private String errorDescription;
}
