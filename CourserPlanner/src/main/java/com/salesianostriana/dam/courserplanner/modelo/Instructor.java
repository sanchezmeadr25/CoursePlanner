package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Instructor {
	
	private String dni;
	private String fotoPerfil;
	private String nombre;
	private String especialidad;
	private String email;
	private double valoracionMedia;
	private String pais;
	private String telefono;
	private LocalDate fechaNacimiento;

}
