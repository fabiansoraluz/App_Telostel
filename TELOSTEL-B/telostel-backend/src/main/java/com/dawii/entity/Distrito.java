package com.dawii.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_distrito")
public class Distrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_distrito")
	private Long id;

	@Column(name = "nombre", length = 35, nullable = false)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "id_departamento")
	private Departamento departamento;
	
	@OneToMany(mappedBy = "distrito")
    private List<Sede> sede;
	
	@OneToMany(mappedBy = "distrito")
    private List<Cliente> cliente;


	public Distrito() {
		super();
	}

	public Distrito(Long id, String nombre, Departamento departamento, List<Sede> sede, List<Cliente> cliente) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.sede = sede;
		this.cliente = cliente;
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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Sede> getSede() {
		return sede;
	}

	public void setSede(List<Sede> sede) {
		this.sede = sede;
	}

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}
	
	

}
