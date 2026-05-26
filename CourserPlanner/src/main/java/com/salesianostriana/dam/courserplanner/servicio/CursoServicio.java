package com.salesianostriana.dam.courserplanner.servicio;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio;

@Service
public class CursoServicio {

	private final CursoRepositorio cursoRepositorio;

	public CursoServicio(CursoRepositorio cursoRepositorio) {
		super();
		this.cursoRepositorio = cursoRepositorio;
	}

	public Curso guardar(Curso curso) {
	//	if (curso.getDuracionHoras() > 0) {
	//		curso.setDuracionHoras(Duration.minusHours(curso.getDuracionHoras()));
	//	}
		return cursoRepositorio.save(curso);
	}

	
	public List<Curso> findAll() {
		return cursoRepositorio.findAll();
	}
	
	public List<Curso> findCursosByInstructor(String dni) {
	    return cursoRepositorio.findByInstructor_Dni(dni);
	}
}
