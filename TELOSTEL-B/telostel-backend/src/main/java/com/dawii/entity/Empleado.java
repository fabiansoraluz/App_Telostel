package com.dawii.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_empleado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado", nullable = false, unique = true, length = 5)
	private Long id;

	@Column(name = "nombre", length = 35, nullable = false)
	private String nombre;

	@Column(name = "apellido", length = 50, nullable = false)
	private String apellido;

	@Column(name = "celular", length = 9, nullable = false)
	private String celular;

	@Column(name = "create_at", nullable = false)
	private LocalDate createAt;

	@Column(name = "estado", nullable = false)
	private Integer estado;

	@PrePersist
	public void prePersist() {
		this.createAt = LocalDate.now();
		this.estado = 1;
	}

}
