package com.salesianostriana.dam.courserplanner.modelo;

import java.time.Duration;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "curso")
public class Curso {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 300)
	private String titulo;
	
	@Column(columnDefinition = "TEXT")
	private String descripcion;
	
	@Column(nullable = false)
	private String categoria;
	
	private String fotoCurso;
	
	
	private Duration duracionHoras;
	
	private double precio;
	
	private int plazasMaximas;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Curso other = (Curso) o;
		return id != null && id.equals(other.getId());
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}