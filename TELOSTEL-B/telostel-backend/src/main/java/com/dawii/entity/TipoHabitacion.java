package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tipo_habitacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoHabitacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_habitacion")
	private Long id;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@Column(name = "descripcion", length = 150, nullable = false)
	private String descripcion;

	@Column(name = "costo", nullable = false)
	private Double costo;

}
