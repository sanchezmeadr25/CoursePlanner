package com.salesianostriana.dam.courserplanner.servicio;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
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

	public void guardar(Curso curso) {
        
        curso.calcularDuracion(); 
        cursoRepositorio.save(curso);
    }
	
	public List<Curso> buscarTodos() {
		return cursoRepositorio.findAll();
	}
	
	public List<Curso> buscarCursoPorInstructor(String dni) {
	    return cursoRepositorio.findByInstructor_Dni(dni);
	}

}
