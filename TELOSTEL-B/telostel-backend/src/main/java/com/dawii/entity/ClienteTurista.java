package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente_turista")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class ClienteTurista extends Cliente{
	
	
	@Column(name = "nacionalidada", length = 50, nullable = false)
    private String nacionalidad;
	
	@Column(name = "celular_emerg", length = 15, nullable = false)
	private String emergencia;

	public ClienteTurista() {
		super();
	}

	public ClienteTurista(Long idTurista, String nacionalidad, String emergencia) {
		super();
		this.nacionalidad = nacionalidad;
		this.emergencia = emergencia;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getEmergencia() {
		return emergencia;
	}

	public void setEmergencia(String emergencia) {
		this.emergencia = emergencia;
	}

	
}
