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
@Table(name="tb_cargo_empleado")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CargoEmpleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cargo")
	private Long id;
	
	@Column(name="cargo",nullable = false,unique = true)
	private String nombre;
	
}
