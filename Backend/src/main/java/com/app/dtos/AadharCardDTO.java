package com.app.dtos;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;

import com.app.entities.Gender;


public class AadharCardDTO {

	@NotBlank(message = "First Name Required !")
	private String firstName;
	@NotBlank(message = "Last Name Required !")
	private String lastName;
	@NotBlank(message = "Date of Birth Required !")
	private LocalDate dob;
	@NotBlank(message = "Gender Required !")
	private Gender gender;
	@NotBlank(message = "State Required !")
	private String state;
	@NotBlank(message = "District Required !")
	private String district;
	@NotBlank(message = "City Required !")
	private String city;
	@NotBlank(message = "Pin Code Required !")
	private String pinCode;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = LocalDate.parse(dob);
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
}
