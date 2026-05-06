package com.salesianostriana.dam.courserplanner.modelo;

import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Curso {
	
	private Long id;
	private String titulo;
	private String descripcion;
	private String categoria;
	private String fotoCurso;
	private Duration duracionHoras;
	private double precio;
	private int plazasMaximas;
	

}
