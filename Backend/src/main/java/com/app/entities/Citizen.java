package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "citizens")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Citizen extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "aadhar_id")
	private AadharCard aadharCard;
	@Column(length = 10)
	private String phoneNo;
	@Column(length = 255)
	private String password;
	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookingList = new ArrayList<>();

	public Citizen(AadharCard aadharCard, String phoneNo, String password) {
		super();
		this.aadharCard = aadharCard;
		this.phoneNo = phoneNo;
		this.password = password;
	}
}
