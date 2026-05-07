package com.salesianostriana.dam.courserplanner.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Curso {
	
	@Id @GeneratedValue
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
	
}
