package com.dawii.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_producto")
public class Producto {
	
	@Id
	@Column(name="id_producto", nullable = false, unique = true, length = 5)
	private String id;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "precio", nullable = false)
	private Double precio;
	
	@Column(name = "stock", nullable = false)
	private Integer stock;
	
	@Column(name = "create_at", nullable = false)
	private LocalDate registro;
	
	@Column(name = "estado", length = 50, nullable = false)
	private Integer estado;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private CategoriaProducto categoria;
	
	@OneToMany(mappedBy = "producto")
	private List<DetalleVenta> detalleVenta;


	public Producto() {
		super();
	}

	public Producto(String id, String nombre, Double precio, Integer stock, LocalDate registro, Integer estado,
			CategoriaProducto categoria, List<DetalleVenta> detalleVenta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.registro = registro;
		this.estado = estado;
		this.categoria = categoria;
		this.detalleVenta = detalleVenta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public LocalDate getRegistro() {
		return registro;
	}

	public void setRegistro(LocalDate registro) {
		this.registro = registro;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public CategoriaProducto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProducto categoria) {
		this.categoria = categoria;
	}

	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	
	
}
