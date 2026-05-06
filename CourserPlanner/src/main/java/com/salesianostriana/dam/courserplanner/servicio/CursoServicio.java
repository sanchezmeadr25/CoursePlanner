package com.salesianostriana.dam.courserplanner.servicio;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Curso;

@Service
public class CursoServicio {

	public List<Curso> getLista(){
		return Arrays.asList(
				new Curso(1l, "Fundamentos de Angular","En esta formación conoceremos los conceptos fundamentales de Angular","Programación","img",Duration.ofHours(6),12.99,6),
				
				new Curso(2l, "Flask Mini-Framework Python","Aprende a crear un sitio web con Flask","Programación","img",Duration.ofHours(2),3.99,50)
				);
	}
}
