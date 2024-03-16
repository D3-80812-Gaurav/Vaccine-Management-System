package com.app.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CenterDTO {
	private Long id;
	private String name;
	private String state;
	private String city;
	private String pincode;
	private int stock;
}
