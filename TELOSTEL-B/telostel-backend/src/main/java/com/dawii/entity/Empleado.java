package com.dawii.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_empleado")
public class Empleado {
	
	@Id
	@Column(name="id_empleado", nullable = false, unique = true, length = 5)
	private String id;

	
	@Column(name = "nombre", length = 35, nullable = false)
	private String nombre;
	
	@Column(name = "apellido", length = 50, nullable = false)
	private String apellido;
	
	@Column(name = "celular", length = 9, nullable = false)
	private String celular;
	
	@Column(name = "create_at", nullable = false)
	private LocalDate registro;
	
	@Column(name = "estado", nullable = false)
	private Integer estado;
	
	@OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
	
	@OneToMany(mappedBy = "empleado")
    private List<Venta> venta;

	@OneToMany(mappedBy = "empleado")
    private List<Reservacion> reservacion;
	
	public Empleado() {
		super();
	}

	public Empleado(String id, String nombre, String apellido, String celular, LocalDate registro, Integer estado,
			Usuario usuario, List<Venta> venta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.registro = registro;
		this.estado = estado;
		this.usuario = usuario;
		this.venta = venta;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Venta> getVenta() {
		return venta;
	}

	public void setVenta(List<Venta> venta) {
		this.venta = venta;
	}


}
