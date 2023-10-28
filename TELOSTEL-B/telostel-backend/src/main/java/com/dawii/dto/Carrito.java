package com.dawii.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {
	
	private Long idProducto;
	private String producto;
	private String idCategoria;
	private double precio;
	private int cantidad;
	private double importe;
	
}
