package com.app.dtos;

import java.time.LocalDate;
import com.app.entities.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetailsDTO {
	private String firstName;
	private String lastName;
	private Long aadharId;
	private Gender gender;
	private String centerName;
	private String centerAddress;
	private LocalDate date;
}
