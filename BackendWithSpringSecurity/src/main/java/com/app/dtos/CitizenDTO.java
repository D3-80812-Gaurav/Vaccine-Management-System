package com.app.dtos;

import java.time.LocalDate;
import com.app.entities.Gender;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CitizenDTO {

	private Long citizenID;
	private Long aadharID;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String state;
	private String district;
	private String city;
	private String pinCode;
	private LocalDate dob;
	private Gender gender;
	
	private boolean hasTakenFirstDose;
	private boolean hasTakenSecondDose;
	private boolean hasReviewedFirstDose;
	private boolean hasReviewedSecondDose;
	private boolean hasBookedFirstDose;
	private boolean hasBookedSecondDose;
}
