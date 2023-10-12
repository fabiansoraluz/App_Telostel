package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente_frecuente")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class ClienteFrecuente extends Cliente{

	
	@Column(name = "puntos", nullable = false)
    private Integer puntos;
	
	@Column(name = "descuento", nullable = false)
	private Double descuento;

	public ClienteFrecuente() {
		super();
	}

	
	public ClienteFrecuente(Long idFrecuente, Integer puntos, Double descuento) {
		super();
		this.puntos = puntos;
		this.descuento = descuento;
	}


	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	
}
