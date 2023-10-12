package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_detalle_venta")
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_venta")
	private Long id;
	
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	@Column(name = "precio", nullable = false)
	private Double precio;
	
	@Column(name = "total", nullable = false)
	private Double total;
	
	@ManyToOne
	@JoinColumn(name = "id_venta")
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	public DetalleVenta() {
		super();
	}

	public DetalleVenta(Long id, Integer cantidad, Double precio, Double total, Venta venta, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
		this.venta = venta;
		this.producto = producto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
}
