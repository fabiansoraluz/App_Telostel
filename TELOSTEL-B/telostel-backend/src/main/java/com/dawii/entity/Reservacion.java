package com.dawii.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_reservacion")
public class Reservacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_reservacion")
	private Long id;
	
	@Column(name = "ingreso", nullable = false)
	private LocalDate ingreso;
	
	@Column(name = "salida", nullable = false)
	private LocalDate salida;
	
	@Column(name = "create_at", nullable = false)
	private LocalDate registro;
	
	@Column(name = "estado", length = 30 ,nullable = false)
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_empleado")
	private Empleado empleado;

	@OneToOne
	@JoinColumn(name = "id_habitacion")
	private Habitacion habitacion;
	
	@OneToOne
	@JoinColumn(name = "id_sede")
	private Sede sede;
	
	@OneToMany(mappedBy = "reservacion")
    private List<PagoReservacion> pagoReservacion;

	public Reservacion() {
		super();
	}

	public Reservacion(Long id, LocalDate ingreso, LocalDate salida, LocalDate registro, String estado, Cliente cliente,
			Empleado empleado, Habitacion habitacion, Sede sede, List<PagoReservacion> pagoReservacion) {
		super();
		this.id = id;
		this.ingreso = ingreso;
		this.salida = salida;
		this.registro = registro;
		this.estado = estado;
		this.cliente = cliente;
		this.empleado = empleado;
		this.habitacion = habitacion;
		this.sede = sede;
		this.pagoReservacion = pagoReservacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getIngreso() {
		return ingreso;
	}

	public void setIngreso(LocalDate ingreso) {
		this.ingreso = ingreso;
	}

	public LocalDate getSalida() {
		return salida;
	}

	public void setSalida(LocalDate salida) {
		this.salida = salida;
	}

	public LocalDate getRegistro() {
		return registro;
	}

	public void setRegistro(LocalDate registro) {
		this.registro = registro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public List<PagoReservacion> getPagoReservacion() {
		return pagoReservacion;
	}

	public void setPagoReservacion(List<PagoReservacion> pagoReservacion) {
		this.pagoReservacion = pagoReservacion;
	}
	

}
