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
@Table(name="centers")
public class Center extends BaseEntity{
	@Column(length=20)
	private String name;
	@Column(length=40)
	private String address;
	private int stock;
	@Column(length=20)
	private String password;
	@OneToMany(mappedBy = "center", cascade = CascadeType.ALL,orphanRemoval=true)
	private  List<Booking> bookingList=new ArrayList<>();
	@OneToMany(mappedBy="center",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<VaccinationRecord> vaccinationRecordList=new ArrayList<>();
}
