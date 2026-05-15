package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "estudiante")
public class Estudiante {

	@Id
	@Column(name = "dni", length = 9, nullable = false)
	private String dni;

	@Column(name = "foto")
	private String fotoPerfil;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "apellidos", nullable = false)
	private String apellidos;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "nivelExperiencia", nullable = false)
	private String nivelExperiencia;

	@Column(name = "telefono", nullable = false)
	private String telefono;

	@Column(name = "fechaNacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	@Column(name = "pais")
	private String pais;
	@Column(name = "localidad")
	private String localidad;

	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<Inscripcion> listaInscripciones = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Estudiante other = (Estudiante) o;
		return dni != null && dni.equals(other.getDni());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}