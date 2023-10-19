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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

	@NotEmpty
	@Column(name = "nombre", length = 35, nullable = false)
	private String nombre;

	@NotEmpty
	@Column(name = "apellido", length = 50, nullable = false)
	private String apellido;
	
	@NotEmpty
	@Size(min = 8,max = 8)
	@Column(name = "dni", length = 8,nullable = false)
	private String dni;

	@NotEmpty
	@Size(min=7,max=9)
	@Column(name = "celular", length = 9, nullable = false)
	private String celular;

	@Column(name = "create_at", nullable = false)
	private LocalDate createAt;

	@Column(name = "estado", nullable = false)
	private Integer estado;

	@NotNull
	@ManyToOne
	@JoinColumn(name="id_ubigeo",nullable = false)
	private Ubigeo ubigeo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_cargo",nullable = false)
	private CargoEmpleado cargo;
	
	@PrePersist
	public void prePersist() {
		this.createAt = LocalDate.now();
		this.estado = 1;
	}

}
