package com.dawii.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_venta")
	private Long id;
	
	@Column(name = "importe", nullable = false)
	private Double importe;
	
	@Column(name = "create_at", nullable = false)
	private LocalDate createAt;
	
	@ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

	@ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="id_venta")
	private List<DetalleVenta> detallesVenta;

	@PrePersist
	private void prePersist() {
		this.createAt=LocalDate.now();
	}
	
}
