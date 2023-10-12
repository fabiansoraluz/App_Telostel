package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_cliente_vip")
@PrimaryKeyJoinColumn(name = "id_cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteVip extends Cliente{
	
	@Column(name = "preferencias", nullable = false)
    private String preferencias;
	
	@Column(name = "beneficios", nullable = false)
	private String beneficios;
	
}
