package com.dawii.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_venta")
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_venta")
	private Long id;
	
	@Column(name = "importe", nullable = false)
	private Double importe;
	
	@Column(name = "create_at", nullable = false)
	private LocalDate registro;
	
	@ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

	@ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

	@OneToMany(mappedBy = "venta")
	private List<DetalleVenta> detallesVenta;


	public Venta() {
		super();
	}

	public Venta(Long id, Double importe, LocalDate registro, Cliente cliente, Empleado empleado,
			List<DetalleVenta> detallesVenta) {
		super();
		this.id = id;
		this.importe = importe;
		this.registro = registro;
		this.cliente = cliente;
		this.empleado = empleado;
		this.detallesVenta = detallesVenta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public LocalDate getRegistro() {
		return registro;
	}

	public void setRegistro(LocalDate registro) {
		this.registro = registro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<DetalleVenta> getDetallesVenta() {
		return detallesVenta;
	}

	public void setDetallesVenta(List<DetalleVenta> detallesVenta) {
		this.detallesVenta = detallesVenta;
	}
	
	

	
}
