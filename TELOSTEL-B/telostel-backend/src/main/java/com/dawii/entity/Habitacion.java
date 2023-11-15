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
@Table(name = "tb_habitacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_habitacion", nullable = false, unique = true, length = 5)
	private Long id;

	@Column(name = "numero", nullable = false)
	private Integer numero;

	@Column(name = "piso", nullable = false)
	private String piso;

	@Column(name = "create_at",nullable=false)
	private LocalDate createAt;
	
	@Column(name = "estado", nullable = false)
	private String estado;

	@ManyToOne
	@JoinColumn(name = "id_tipo_habitacion")
	private TipoHabitacion tipo;

	@PrePersist
	private void prePersist() {
		this.createAt=LocalDate.now();
		this.estado="disponible";
	}
	
}
