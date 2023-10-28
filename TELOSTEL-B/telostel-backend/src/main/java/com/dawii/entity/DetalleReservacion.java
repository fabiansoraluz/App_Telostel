package com.dawii.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_detalle_reservacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleReservacion {
	
	@EmbeddedId
	private DetalleReservacionPK pk;
	
	@ManyToOne
	@JoinColumn(name="id_reservacion",insertable = false, updatable = false)
	@JsonIgnore
	private Reservacion reservacion;
	
	@ManyToOne
	@JoinColumn(name="id_servicio",insertable = false,updatable = false)
	@JsonIgnore
	private Servicio servicio;
	
	private String descripcion;
	private Double costo;
	private int cantidad;
	private Double importe;
	
	@PrePersist
	private void prePersist() {
		this.cantidad = 1;
		this.importe = costo * cantidad;
	}
	
}
