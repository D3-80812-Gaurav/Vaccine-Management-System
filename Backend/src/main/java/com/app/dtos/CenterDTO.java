package com.app.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CenterDTO {
	private String name;
	private String state;
	private String district;
	private String city;
	private String pinCode;
	private int stock;
}
