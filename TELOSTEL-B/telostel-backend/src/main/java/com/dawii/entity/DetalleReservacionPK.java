package com.dawii.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Data
public class DetalleReservacionPK implements Serializable{

	@Column(name="id_reservacion")
	private Long reservacion;
	
	@Column(name="id_servicio")
	private Long servicio;
	
	private static final long serialVersionUID = 1L;
}
