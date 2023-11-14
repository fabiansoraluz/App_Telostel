package com.dawii.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VentaDTO {

    private LocalDate fecha;
    private Double importeTotal;

    public VentaDTO(LocalDate fecha, Double importeTotal) {
        this.fecha = fecha;
        this.importeTotal = importeTotal;
    }
}