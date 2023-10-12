package com.dawii.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_documento")
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_documento")
	private Long id;
	
	@Column(name = "descripcion", length = 9, nullable = false, unique = true)
	private String descripcion;
	
	@OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

	public Documento() {
		super();
	}

	public Documento(Long id, String descripcion, Cliente cliente) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	

}
