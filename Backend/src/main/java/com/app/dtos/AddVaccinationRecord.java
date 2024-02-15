package com.app.dtos;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddVaccinationRecord {
	@NotNull(message="AadharId Required !")
	private Long aadharCardId;
	@NotNull(message="CenterId Required !")
	private Long centerId;
}
