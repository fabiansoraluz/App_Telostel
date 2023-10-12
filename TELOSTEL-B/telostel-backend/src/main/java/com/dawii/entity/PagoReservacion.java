package com.dawii.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pago_reservacion")
public class PagoReservacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pago_reservacion")
	private Long id;
	
	@Column(name = "anticipo", nullable = false)
	private Double anticipo;

	@Column(name = "servicio", nullable = false)
	private Double servicio;

	@Column(name = "subtotal", nullable = false)
	private Double subtotal;

	@Column(name = "descuento", nullable = false)
	private Double descuento;

	@Column(name = "total", nullable = false)
	private Double total;

	@Column(name = "create_at", nullable = false)
	private LocalDate registro;
	
	@ManyToOne
	@JoinColumn(name = "id_reservacion")
	private Reservacion reservacion;

	public PagoReservacion() {
		super();
	}

	public PagoReservacion(Long id, Double anticipo, Double servicio, Double subtotal, Double descuento, Double total,
			LocalDate registro, Reservacion reservacion) {
		super();
		this.id = id;
		this.anticipo = anticipo;
		this.servicio = servicio;
		this.subtotal = subtotal;
		this.descuento = descuento;
		this.total = total;
		this.registro = registro;
		this.reservacion = reservacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAnticipo() {
		return anticipo;
	}

	public void setAnticipo(Double anticipo) {
		this.anticipo = anticipo;
	}

	public Double getServicio() {
		return servicio;
	}

	public void setServicio(Double servicio) {
		this.servicio = servicio;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDate getRegistro() {
		return registro;
	}

	public void setRegistro(LocalDate registro) {
		this.registro = registro;
	}

	public Reservacion getReservacion() {
		return reservacion;
	}

	public void setReservacion(Reservacion reservacion) {
		this.reservacion = reservacion;
	}

	

}
