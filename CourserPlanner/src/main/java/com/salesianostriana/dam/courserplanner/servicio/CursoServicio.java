package com.salesianostriana.dam.courserplanner.servicio;

import java.util.List;

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
	
	public Curso guardar(Curso curso) {
        return cursoRepositorio.save(curso);
    }
    
    // Método adicional para listar todos los cursos (te servirá luego)
    public List<Curso> findAll() {
        return cursoRepositorio.findAll();
    }
}
