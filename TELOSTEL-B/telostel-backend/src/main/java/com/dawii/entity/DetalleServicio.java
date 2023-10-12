package com.dawii.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_detalle_servicio")
@IdClass(value = DetalleServicioPK.class)
public class DetalleServicio {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")    
	private Servicio servicio;
	
	@Id
	@ManyToOne
    @JoinColumn(name = "id_reservacion", referencedColumnName = "id_reservacion")
    private Reservacion reservacion;

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Reservacion getReservacion() {
		return reservacion;
	}

	public void setReservacion(Reservacion reservacion) {
		this.reservacion = reservacion;
	}
	
	
	
}
