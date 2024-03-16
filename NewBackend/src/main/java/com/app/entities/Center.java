package com.app.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "centers")
public class Center extends UserEntity {
	@Column(length = 40)
	private String name;
	@Column(length = 40)
	private String state;
	@Column(length = 40)
	private String city;
	@Column(length = 10, name = "pin_code")
	private String pincode;
	private int stock;
	@OneToMany(mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookingList = new ArrayList<>();
	@OneToMany(mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VaccinationRecord> vaccinationRecordList = new ArrayList<>();
	
	{
		this.role=Role.CITIZEN;
	}

	public Center(String name, String state, String city, String pincode, int stock) {
		super();
		this.name = name;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.stock = stock;
	}
	public Center(String name, String state, String city, String pincode, int stock,String password) {
		super();
		this.name = name;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.stock = stock;
		super.setPassword(password);
		super.setRole(Role.CENTER);
	}

}
