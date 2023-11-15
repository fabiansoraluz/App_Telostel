package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_ubigeo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ubigeo {

	@Id
	@Column(name="id_ubigeo", length = 6, nullable = false, unique = true)
	private String id;
	
	@Column(name="departamento", length = 50, nullable = false)
	private String departamento;
	
	@Column(name="provincia", length = 50, nullable = false)
	private String provincia;
	
	@Column(name="distrito", length = 50, nullable = false)
	private String distrito;
	
}
