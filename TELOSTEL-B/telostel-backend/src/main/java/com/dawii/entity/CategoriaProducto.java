package com.dawii.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_categoria_producto")
public class CategoriaProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_categoria")
	private Long id;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", length = 120, nullable = false)
	private String descripcion;

	@OneToMany(mappedBy = "categoria")
    private List<Producto> producto;
	
	public CategoriaProducto() {
		super();
	}

	public CategoriaProducto(Long id, String nombre, String descripcion, List<Producto> producto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.producto = producto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}
	

	
}
