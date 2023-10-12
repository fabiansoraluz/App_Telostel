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
@Table(name = "tb_rol")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_rol")
	private Long id;
	
	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@OneToMany(mappedBy = "rol")
    private List<Usuario> usuario;
	
	public Rol() {
		super();
	}

	public Rol(Long id, String nombre, List<Usuario> usuario, List<com.dawii.entity.RolEnlace> rolEnlace) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
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


	public List<Usuario> getUsuario() {
		return usuario;
	}


	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	
	
}
