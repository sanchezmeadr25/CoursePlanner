package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public abstract class Estudiante {

	@Id @GeneratedValue
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
