package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_habitacion")
public class Habitacion {

	@Id
	@Column(name="id_habitacion", nullable = false, unique = true, length = 5)
	private String id;
	
	@Column(name = "numero", nullable = false)
	private Integer numero;
	
	@Column(name = "piso", nullable = false)
	private Integer piso;
	
	@Column(name = "estado", nullable = false)
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_habitacion")
	private TipoHabitacion tipohabitacion;
	
	@OneToOne(mappedBy = "habitacion")
	private Reservacion reservacion;


	public Habitacion() {
		super();
	}

	public Habitacion(String id, Integer numero, Integer piso, String estado, TipoHabitacion tipohabitacion,
			Reservacion reservacion) {
		super();
		this.id = id;
		this.numero = numero;
		this.piso = piso;
		this.estado = estado;
		this.tipohabitacion = tipohabitacion;
		this.reservacion = reservacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoHabitacion getTipohabitacion() {
		return tipohabitacion;
	}

	public void setTipohabitacion(TipoHabitacion tipohabitacion) {
		this.tipohabitacion = tipohabitacion;
	}

	public Reservacion getReservacion() {
		return reservacion;
	}

	public void setReservacion(Reservacion reservacion) {
		this.reservacion = reservacion;
	}

	
	
}
