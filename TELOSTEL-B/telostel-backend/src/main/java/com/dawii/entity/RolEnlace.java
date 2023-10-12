package com.dawii.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_rol_enlace")
@IdClass(value = RolEnlacePK.class)
public class RolEnlace {

	@Id
	@ManyToOne
	@JoinColumn(name = "id_rol", referencedColumnName = "id_rol")    
	private Rol rol;
	
	@Id
	@ManyToOne
    @JoinColumn(name = "id_enlace", referencedColumnName = "id_enlace")
    private Enlace enlace;

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Enlace getEnlace() {
		return enlace;
	}

	public void setEnlace(Enlace enlace) {
		this.enlace = enlace;
	}
	
	
	
}
