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
public class Center extends BaseEntity {
	@Column(length = 40)
	private String name;
	@Column(length = 40)
	private String state;
	@Column(length = 40)
	private String district;
	@Column(length = 40)
	private String city;
	@Column(length = 10, name = "pin_code")
	private String pincode;
	private int stock;
	@Column(length = 255)
	private String password;
	@OneToMany(mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookingList = new ArrayList<>();
	@OneToMany(mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VaccinationRecord> vaccinationRecordList = new ArrayList<>();

	public Center(String name, String state, String district, String city, String pincode, int stock, String password) {
		super();
		this.name = name;
		this.state = state;
		this.district = district;
		this.city = city;
		this.pincode = pincode;
		this.stock = stock;
		this.password = password;
	}

}
