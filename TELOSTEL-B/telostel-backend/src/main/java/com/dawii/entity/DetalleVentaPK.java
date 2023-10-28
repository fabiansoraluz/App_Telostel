package com.dawii.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Embeddable
public class DetalleVentaPK implements Serializable{

	@Column(name="id_venta")
	private Long venta;
	@Column(name="id_producto")
	private Long producto;
	
	private static final long serialVersionUID = 1L;
}
