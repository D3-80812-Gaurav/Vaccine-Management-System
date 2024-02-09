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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="reviews")
public class Review extends BaseEntity{
	private int rating;
	@Column(length=100)
	private String comment;
	@OneToMany(mappedBy="review",cascade=CascadeType.ALL)
	private List<VaccinationRecord> vaccinationRecordList=new ArrayList<>();
}
