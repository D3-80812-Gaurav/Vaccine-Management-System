package com.app.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CenterSignInRequest {
	@NotNull
	private Long centerId;
	@NotBlank(message="Password Required !")
	private String password;
}
