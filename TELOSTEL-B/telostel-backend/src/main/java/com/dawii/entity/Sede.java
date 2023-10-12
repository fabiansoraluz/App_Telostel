package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sede")
public class Sede {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sede")
	private Long id;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@Column(name = "direccion", length = 120, nullable = false)
	private String direccion;
	
	@Column(name = "imagen", nullable = false)
	private String imagen;
	
	@ManyToOne
	@JoinColumn(name = "id_distrito")
	private Distrito distrito;
	
	@OneToOne(mappedBy = "sede")
	private Reservacion reservacion;


	public Sede() {
		super();
	}

	public Sede(Long id, String nombre, String direccion, String imagen, Distrito distrito, Reservacion reservacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.imagen = imagen;
		this.distrito = distrito;
		this.reservacion = reservacion;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Reservacion getReservacion() {
		return reservacion;
	}

	public void setReservacion(Reservacion reservacion) {
		this.reservacion = reservacion;
	}

	
	
	
	
}
