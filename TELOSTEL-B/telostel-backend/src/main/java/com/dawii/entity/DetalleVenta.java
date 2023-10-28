package com.dawii.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_detalle_venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVenta {

	@EmbeddedId
	private DetalleVentaPK pk;
	
	@ManyToOne
	@JoinColumn(name = "id_venta", insertable = false, updatable = false)
	@JsonIgnore
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "id_producto", insertable = false, updatable = false)
	@JsonIgnore
	private Producto producto;
	
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	@Column(name = "precio", nullable = false)
	private Double precio;
	
	@Column(name = "total", nullable = false)
	private Double total;
	
	@PrePersist
	private void prePersist() {
		this.total=this.cantidad*this.precio;
	}
}
