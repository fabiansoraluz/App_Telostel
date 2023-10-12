package com.dawii.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_enlace")
public class Enlace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_enlace")
	private Long id;
	
	@Column(name = "descripcion", length = 50, nullable = false)
	private String descripcion;
	
	@Column(name = "ruta", length = 50, nullable = false)
	private String ruta;
	
	public Enlace() {
		super();
	}

	public Enlace(Long id, String descripcion, String ruta, List<com.dawii.entity.RolEnlace> rolEnlace) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.ruta = ruta;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	

}
