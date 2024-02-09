package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="aadhar_card")
@ToString(callSuper = true, exclude = {"citizen"})
public class AadharCard extends BaseEntity {
	
	@Column(length=20)
	private String firstName;
	@Column(length=20)
	private String lastName;
	private LocalDate dob;
	@Enumerated(EnumType.STRING)
	@Column(length=10)
	private Gender gender;
	@Column(length=20)
	private String state;
	@Column(length=20)
	private String district;
	@Column(length=20)
	private String city;
	@Column(length = 6, name = "pin_code")
	private String pinCode;
	
	@OneToOne(mappedBy = "aadharCard")
	private Citizen citizen;	
}
