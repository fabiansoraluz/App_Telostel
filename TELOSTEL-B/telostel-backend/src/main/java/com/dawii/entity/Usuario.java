package com.dawii.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", nullable = false, unique = true, length = 5)
	private Long id;

	@Column(name = "username", length = 30, nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "imagen", nullable = false)
	private String imagen;

	@Column(name = "create_at", nullable = false)
	private LocalDate createAt;

	@Column(name = "estado", nullable = false)
	private Integer estado;

	@OneToOne
	@JoinColumn(name="id_empleado")
	private Empleado empleado;

	@ManyToMany
	@JoinTable(
			name = "tb_usuario_rol",
			joinColumns = @JoinColumn(name="id_usuario",referencedColumnName = "id_usuario"),
			inverseJoinColumns = @JoinColumn(name="id_rol",referencedColumnName = "id_rol"))
	private List<Rol> roles;

	@PrePersist
	private void prePersist() {
		this.createAt=LocalDate.now();
		this.estado=1;
	}
	
}
