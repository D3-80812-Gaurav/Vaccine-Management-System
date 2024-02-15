package com.app.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class BookingDTO {
	private String firstName;
	private String lastName;
	private Long aadharId;
	private LocalDate date;
}
