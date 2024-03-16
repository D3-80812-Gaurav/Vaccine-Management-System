package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "citizens")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Citizen extends UserEntity {
	@OneToOne
	@JoinColumn(name = "aadhar_id")
	private AadharCard aadharCard;
	@Column(length = 10)
	private String phoneNo;
	
	{
		this.role=Role.CITIZEN;
	}
	
	public Citizen(AadharCard aadharCard, String phoneNo, String password) {
		super(password,Role.CITIZEN);
		this.aadharCard = aadharCard;
		this.phoneNo = phoneNo;
	}
}
