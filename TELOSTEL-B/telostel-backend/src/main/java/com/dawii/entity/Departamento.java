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
@Table(name = "tb_departamento")
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_departamento")
	private Long id;
	

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@OneToMany(mappedBy = "departamento")
    private List<Distrito> distritos;

	public Departamento() {
		super();
	}


	public Departamento(Long id, String nombre, List<Distrito> distritos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.distritos = distritos;
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


	public List<Distrito> getDistritos() {
		return distritos;
	}


	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}
	
	

	
}
