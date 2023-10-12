package com.dawii.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tipo_habitacion")
public class TipoHabitacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_habitacion")
	private Long id;
	
	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", length = 150, nullable = false)
	private String descripcion;
	
	@Column(name = "costo", nullable = false)
	private Double costo;
	
	@OneToMany(mappedBy = "tipohabitacion")
    private List<Habitacion> habitacion;

	public TipoHabitacion() {
		super();
	}


	public TipoHabitacion(Long id, String nombre, String descripcion, Double costo, List<Habitacion> habitacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.habitacion = habitacion;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Double getCosto() {
		return costo;
	}


	public void setCosto(Double costo) {
		this.costo = costo;
	}


	public List<Habitacion> getHabitacion() {
		return habitacion;
	}


	public void setHabitacion(List<Habitacion> habitacion) {
		this.habitacion = habitacion;
	}

	
	
}
