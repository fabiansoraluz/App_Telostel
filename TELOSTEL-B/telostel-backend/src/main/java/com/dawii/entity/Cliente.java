package com.dawii.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {

	@Id
	@Column(name="id_cliente", nullable = false, unique = true, length = 5)
	private String id;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "apellido", length = 50, nullable = false)
	private String apellido;
		
	@Column(name = "celular", length = 9, nullable = false)
	private String celular;
	
	@Column(name = "create_at", nullable = false)
	private LocalDate registro;
	
	@Column(name = "estado", nullable = false)
	private Integer estado;
	
	@ManyToOne
	@JoinColumn(name = "id_distrito")
	private Distrito distrito;

	@OneToOne(mappedBy = "cliente")
    private Documento documento;
	
	@OneToMany(mappedBy = "cliente")
    private List<Venta> venta;
	
	@OneToMany(mappedBy = "cliente")
    private List<Reservacion> reservacion;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String id, String nombre, String apellido, String celular, LocalDate registro, Integer estado,
			Distrito distrito, Documento documento, List<Venta> venta, List<Reservacion> reservacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.registro = registro;
		this.estado = estado;
		this.distrito = distrito;
		this.documento = documento;
		this.venta = venta;
		this.reservacion = reservacion;
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

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public List<Venta> getVenta() {
		return venta;
	}

	public void setVenta(List<Venta> venta) {
		this.venta = venta;
	}

	public List<Reservacion> getReservacion() {
		return reservacion;
	}

	public void setReservacion(List<Reservacion> reservacion) {
		this.reservacion = reservacion;
	}
	
	
	
}
