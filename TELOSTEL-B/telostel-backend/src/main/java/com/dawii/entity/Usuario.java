package com.dawii.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@Column(name="id_usuario", nullable = false, unique = true, length = 5)
	private String id;
	
	@Column(name = "username", length = 30, nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "imagen", nullable = false)
	private String imagen;
	
	@Column(name = "create_at", nullable = false)
	private LocalDate registro;
	
	@Column(name = "estado", nullable = false)
	private Integer estado;
	
	@OneToOne(mappedBy = "usuario")
    private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "id_rol")
	private Rol rol;

	public Usuario() {
		super();
	}

	public Usuario(String id, String username, String password, String imagen, LocalDate registro, Integer estado,
			Empleado empleado, Rol rol) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.imagen = imagen;
		this.registro = registro;
		this.estado = estado;
		this.empleado = empleado;
		this.rol = rol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public LocalDate getRegistro() {
		return registro;
	}

	public void setRegistro(LocalDate registro) {
		this.registro = registro;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	

	
}
