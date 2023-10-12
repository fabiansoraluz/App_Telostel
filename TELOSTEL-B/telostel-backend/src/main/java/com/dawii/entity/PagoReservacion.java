package com.dawii.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pago_reservacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	private LocalDate createAt;

	@ManyToOne
	@JoinColumn(name = "id_reservacion")
	private Reservacion reservacion;
	
	@PrePersist
	public void prePersist() {
		this.createAt=LocalDate.now();
	}
}
