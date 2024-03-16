package com.app.dtos;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CenterSignInRequest {
	@NotBlank(message = "email required")
	private String email;
	@NotBlank(message = "password required")
	private String password;
}
