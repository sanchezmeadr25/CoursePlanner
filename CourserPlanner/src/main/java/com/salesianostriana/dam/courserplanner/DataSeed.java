package com.salesianostriana.dam.courserplanner;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.repositorio.EstudianteRepositorio;
import com.salesianostriana.dam.courserplanner.servicio.EstudianteServicio;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeed {
	
	private final EstudianteServicio estudianteServicio;
	
	@PostConstruct
	public void run() {
		Estudiante e = Estudiante.builder()
				.dni("12345678A") 
				.nombre("Juan")
				.apellidos("Suarez")
				.email("juan@gmail.com")
				.nivelExperiencia("bajo")
				.telefono("722183453")
				.fechaNacimiento(LocalDate.of(2026, 05, 30))
				.pais("España")
				.localidad("Sevilla")
				.build();
		
		estudianteServicio.guardar(e);
	}
}
