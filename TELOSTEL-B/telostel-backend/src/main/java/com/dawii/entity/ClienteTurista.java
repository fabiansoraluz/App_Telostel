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
@Table(name = "tb_cliente_turista")
@PrimaryKeyJoinColumn(name = "id_cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteTurista extends Cliente {

	@Column(name = "nacionalidada", length = 50, nullable = false)
	private String nacionalidad;

	@Column(name = "celular_emerg", length = 15, nullable = false)
	private String emergencia;

}
