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
public class Citizen extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "aadhar_id")
	private AadharCard aadharCard;
	@Column(length = 10)
	private String phoneNo;
	@Column(length = 255)
	private String password;

	public Citizen(AadharCard aadharCard, String phoneNo, String password) {
		super();
		this.aadharCard = aadharCard;
		this.phoneNo = phoneNo;
		this.password = password;
	}
}
