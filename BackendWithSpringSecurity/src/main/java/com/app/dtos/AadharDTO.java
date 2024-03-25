package com.app.dtos;

import java.time.LocalDate;
import com.app.entities.Gender;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AadharDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private Gender gender;
	private String state;
	private String city;
	private String pinCode;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{ ID:"+id+",FirstName:"+firstName+",LastName:"+lastName+",DOB:"+dob.toString()+",Gender:"+gender.toString()+",State:"+state+",City:"+city+",Pincode:"+pinCode+" }";
	}
}
