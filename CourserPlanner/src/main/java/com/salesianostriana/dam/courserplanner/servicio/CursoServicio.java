package com.salesianostriana.dam.courserplanner.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
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

	public Optional<Curso> buscarPorId(Long cursoId) {
		return cursoRepositorio.findById(cursoId);
	}

	public void editar(Curso curso) {
		curso.calcularDuracion();
		cursoRepositorio.save(curso);
	}

}
