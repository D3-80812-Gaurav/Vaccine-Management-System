package com.app.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class NewCenterRegistrationDTO {
	@NotBlank(message = "Please Provide Center Name")
	private String name;
	@NotBlank(message = "Please Provide Center State")
	private String state;
	@NotBlank(message = "Please Provide Center District")
	private String district;
	@NotBlank(message = "Please Provide Center City")
	private String city;
	@NotBlank(message = "Please Provide Center Pincode")
	private String pinCode;
	@NotNull(message = "Please Provide Initial Vaccine Stock")
	private int stock;
	@NotBlank(message = "Please Provide Password")
	private String password;
}
