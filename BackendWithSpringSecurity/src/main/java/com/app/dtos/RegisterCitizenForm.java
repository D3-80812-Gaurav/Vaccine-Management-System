package com.app.dtos;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCitizenForm {
	@NotBlank(message = "Please Provide Citizen AadharId")
	private String aadharId;
	@NotBlank(message = "Please Provide Citizen Email")
	private String email;
	@NotBlank(message = "Please Provide Citizen Phone No")
	private String phoneNo;
	@NotBlank(message = "Please Provide Citizen Password")
	private String password;
}
