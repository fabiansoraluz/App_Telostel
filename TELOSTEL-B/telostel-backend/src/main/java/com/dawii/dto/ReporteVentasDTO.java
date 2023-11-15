package com.dawii.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReporteVentasDTO {

	private Long idVenta;
    private LocalDate createAt;
    private Double importe;
    private String cliente;
    private String empleado;
    private Double importeTotal;
}
	