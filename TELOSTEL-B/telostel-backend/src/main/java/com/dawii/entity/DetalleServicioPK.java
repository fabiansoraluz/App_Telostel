package com.dawii.entity;

import java.io.Serializable;
import java.util.Objects;

public class DetalleServicioPK implements Serializable{

	private Long servicio;
	private Long reservacion;
	
	@Override
	public int hashCode() {
		return Objects.hash(reservacion, servicio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleServicioPK other = (DetalleServicioPK) obj;
		return Objects.equals(reservacion, other.reservacion) && Objects.equals(servicio, other.servicio);
	}
	
	
}
