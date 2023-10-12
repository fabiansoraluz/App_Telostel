package com.dawii.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_enlace")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enlace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_enlace")
	private Long id;
	
	@Column(name = "descripcion", length = 50, nullable = false)
	private String descripcion;
	
	@Column(name = "ruta", length = 50)
	private String ruta;
	
	@Column(name = "ico", length = 50)
	private String ico;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_enlace")
	private List<SubEnlace> subenlaces;
	
}
