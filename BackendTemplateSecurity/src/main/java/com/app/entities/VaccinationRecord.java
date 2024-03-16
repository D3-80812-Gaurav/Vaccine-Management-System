package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vaccination_records")
public class VaccinationRecord extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "center_id")
	private Center center;
	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;
	@ManyToOne
	@JoinColumn(name = "addhar_id")
	private AadharCard aadharCard;
	private LocalDate date;
}
