package com.app.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminSignInRequest {
	@NotBlank(message = "name required")
	private String name;
	@NotBlank(message = "password required")
	private String password;
}
