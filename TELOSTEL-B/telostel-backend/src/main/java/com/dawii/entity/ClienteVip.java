package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente_vip")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class ClienteVip extends Cliente{
	
	@Column(name = "preferencias", nullable = false)
    private String preferencias;
	
	@Column(name = "beneficios", nullable = false)
	private String beneficios;

	public ClienteVip() {
		super();
	}

	public ClienteVip(Long idVip, String preferencias, String beneficios) {
		super();
		this.preferencias = preferencias;
		this.beneficios = beneficios;
	}

	public String getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(String preferencias) {
		this.preferencias = preferencias;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}
	
}
