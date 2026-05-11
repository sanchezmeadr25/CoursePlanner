package com.salesianostriana.dam.courserplanner.modelo;

import java.time.Duration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="curso")
public class Curso {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	private String titulo;
	private String descripcion;
	private String categoria;
	private String fotoCurso;
	private Duration duracionHoras;
	private double precio;
	private int plazasMaximas;
	
	@OneToMany
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
