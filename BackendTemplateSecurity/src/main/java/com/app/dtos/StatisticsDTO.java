package com.app.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StatisticsDTO {
	private Long totalVaccinations;
	private Long maleVaccinations;
	private Long femaleVaccinations;
	private Long totalAppointments;
}
