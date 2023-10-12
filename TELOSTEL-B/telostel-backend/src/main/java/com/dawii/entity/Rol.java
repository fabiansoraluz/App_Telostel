package com.dawii.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_rol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Long id;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@ManyToMany
	@JoinTable(
			name="tb_rol_enlace",
			joinColumns = @JoinColumn(name="id_rol",referencedColumnName = "id_rol"),
			inverseJoinColumns = @JoinColumn(name="id_enlace",referencedColumnName = "id_enlace"))
	private List<Enlace> enlaces;
	
}
