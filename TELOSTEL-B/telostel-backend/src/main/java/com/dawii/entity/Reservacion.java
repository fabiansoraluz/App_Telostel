package com.dawii.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_reservacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reservacion")
	private Long id;

	@Column(name = "checkIn", nullable = false)
	private LocalDate checkIn;

	@Column(name = "checkOut", nullable = false)
	private LocalDate checkOut;

	@Column(name = "create_at", nullable = false)
	private LocalDate createAt;

	@Column(name = "estado", length = 30, nullable = false)
	private Integer estado;

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

	@ManyToMany
	@JoinTable(
			name="tb_reservacion_servicio",
			joinColumns = @JoinColumn(name="id_reservacion",referencedColumnName = "id_reservacion"),
			inverseJoinColumns = @JoinColumn(name="id_servicio",referencedColumnName = "id_servicio"))
	private List<Servicio> servicios;
	
	@PrePersist
	private void prePersist() {
		this.createAt = LocalDate.now();
		this.estado = 1;
	}

}
