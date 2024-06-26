package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
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
@Table(name = "aadhar_cards")
@ToString(callSuper = true, exclude = { "citizen" })
public class AadharCard extends BaseEntity {
	@Column(length = 40)
	private String firstName;
	@Column(length = 40)
	private String lastName;
	private LocalDate dob;
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Gender gender;
	@Column(length = 40)
	private String state;
	@Column(length = 40)
	private String city;
	@Column(length = 10, name = "pin_code")
	private String pinCode;
	@OneToOne(mappedBy = "aadharCard")
	private Citizen citizen;
	@OneToOne(mappedBy = "aadharCard")
	private Booking booking;
	@OneToMany(mappedBy = "aadharCard", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VaccinationRecord> vaccinationRecordList = new ArrayList<>();

	public AadharCard(String firstName, String lastName, LocalDate dob, Gender gender, String state,
			String city, String pinCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.state = state;
		this.city = city;
		this.pinCode = pinCode;
	}

	public AadharCard(Long id) {
		super(id);
	}
}
