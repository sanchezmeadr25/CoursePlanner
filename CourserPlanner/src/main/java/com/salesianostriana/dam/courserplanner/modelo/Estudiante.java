package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Estudiante {

	private String dni;
	private String fotoPerfil;
	private String nombre;
	private String apellidos;
	private String email;
	private String nivelExperiencia;
	private String telefono;
	private LocalDate fechaNacimiento;
	private String Pais;
	private String Localidad;
	
	
	
}
