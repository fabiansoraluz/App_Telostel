package com.dawii.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaReserva {
	
	private int numero;
	private LocalDate checkIn;
	private LocalDate checkOut;
}
