package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Instructor {
	
	@Id @GeneratedValue
	private String dni;
	private String fotoPerfil;
	private String nombre;
	private String especialidad;
	private String email;
	private double valoracionMedia;
	private String pais;
	private String telefono;
	private LocalDate fechaNacimiento;

	@ManyToOne
	private List<Curso> curso= new ArrayList<>();
	
}
