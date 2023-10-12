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
@Table(name="tb_sub_enlace")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubEnlace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sub_enlace")
	private long id;
	
	@Column(name="descripcion",length = 50,nullable = false)
	private String descripcion;
	
	@Column(name="ruta",length = 50,nullable = false)
	private String ruta;
	
	@Column(name = "ico", length = 50)
	private String ico;
	
}
