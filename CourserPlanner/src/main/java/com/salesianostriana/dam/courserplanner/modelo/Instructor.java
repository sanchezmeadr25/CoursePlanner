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
@Table(name = "instructor")
public class Instructor {
	
	@Id 
	@Column(name = "dni", length = 9)
	private String dni; 
	
	@Column(name = "foto")
	private String fotoPerfil;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "especialidad", nullable = false)
	private String especialidad; 
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "valoracion")
	private double valoracionMedia;
	
	@Column(name = "pais")
	private String pais;
	
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	@Column(name = "fechaNacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	
	@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	// @ToString.Exclude PREGUNTAR A LUISMI
	private List<Curso> curso = new ArrayList<>();
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Instructor other = (Instructor) o;
		return dni != null && dni.equals(other.getDni());
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}