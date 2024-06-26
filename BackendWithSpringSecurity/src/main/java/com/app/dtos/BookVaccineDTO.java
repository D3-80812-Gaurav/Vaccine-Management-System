package com.app.dtos;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookVaccineDTO {
	@NotNull(message="Center Id Required !")
	private Long centerId;
	@NotBlank(message="Date is required !")
	private LocalDate date;
}
