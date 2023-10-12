package com.dawii.entity;

import java.io.Serializable;
import java.util.Objects;

public class RolEnlacePK implements Serializable {

	private Long rol;
	private Long enlace;
	
	@Override
	public int hashCode() {
		return Objects.hash(enlace, rol);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolEnlacePK other = (RolEnlacePK) obj;
		return enlace == other.enlace && rol == other.rol;
	}
	
	public Long getRol() {
		return rol;
	}
	public void setRol(Long rol) {
		this.rol = rol;
	}
	public Long getEnlace() {
		return enlace;
	}
	public void setEnlace(Long enlace) {
		this.enlace = enlace;
	}
	
}
