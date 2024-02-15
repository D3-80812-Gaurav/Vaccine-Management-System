package com.app.dtos;

import com.app.entities.Gender;

public class VaccinationCertificate {
	
	private String firstName;
	private String lastName;
	private int age;
	private Long aadharId;
	private Gender gender;
	private String vaccinationStatus;
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Long getAadharId() {
		return aadharId;
	}
	public void setAadharId(Long aadharId) {
		this.aadharId = aadharId;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getVaccinationStatus() {
		return vaccinationStatus;
	}
	public void setVaccinationStatus(String vaccinationStatus) {
		this.vaccinationStatus = vaccinationStatus;
	}
	@Override
	public String toString() {
		return "Name: " + firstName +" "+ lastName + "\nAge:" + age
				+ "\nAadharCard Id: " + aadharId + "\nGender :" + gender + "\nVaccination Status:" + vaccinationStatus;
	}
	
}
