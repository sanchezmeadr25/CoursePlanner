package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuarios")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {

	@Id
	@Column(name = "dni", length = 9, nullable = false)
	protected String dni;

	@Column(name = "foto")
	private String fotoPerfil;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "apellidos", nullable = false)
	private String apellidos;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "telefono", nullable = false)
	private String telefono;

	@Column(name = "fechaNacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	@Column(name = "pais")
	private String pais;
	
	@Column(name = "localidad")
	private String localidad;
	
	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol; 
}
