package com.dawii.entity;

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
@Table(name = "tb_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto", nullable = false, unique = true, length = 5)
	private Long id;

	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;

	@Column(name = "cant_uni",length = 100,nullable = false)
	private String cantUnidad;
	
	@Column(name = "precio", nullable = false)
	private Double precio;

	@Column(name = "stock", nullable = false)
	private Integer stock;

	@Column(name = "estado", length = 50, nullable = false)
	private Integer estado;
	


	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private CategoriaProducto categoria;

	@PrePersist
	private void prePersits() {
		this.estado=1;
	}
	
}
