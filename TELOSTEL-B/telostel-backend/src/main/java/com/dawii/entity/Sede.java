package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_sede")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sede {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sede")
	private Long id;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@Column(name = "direccion", length = 120, nullable = false)
	private String direccion;
	
	@Column(name = "imagen", nullable = false)
	private String imagen;
	
	@ManyToOne
	@JoinColumn(name = "id_distrito")
	private Distrito distrito;
}
