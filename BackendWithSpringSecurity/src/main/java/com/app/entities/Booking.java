package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Booking extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "aadhar_id")
	private AadharCard aadharCard;
	@ManyToOne
	@JoinColumn(name = "center_id")
	private Center center;
	private LocalDate date;
}
