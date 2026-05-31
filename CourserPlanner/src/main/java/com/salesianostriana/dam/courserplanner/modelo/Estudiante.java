package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@ToString(exclude = {"inscripciones"})
public class Estudiante extends Usuario {

	@NotEmpty(message = "El nivel de experiencia no puede estar vacío")
	@Column(name = "nivelExperiencia", nullable = false)
	private String nivelExperiencia;

	
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

	
	public Estudiante() {
	    super();
	}
	
}