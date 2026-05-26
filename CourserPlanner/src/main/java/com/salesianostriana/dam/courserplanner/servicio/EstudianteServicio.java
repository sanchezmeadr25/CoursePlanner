package com.salesianostriana.dam.courserplanner.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.repositorio.EstudianteRepositorio;
import com.salesianostriana.dam.courserplanner.serviciobase.ServicosBasesImplementados;

@Service
public class EstudianteServicio extends ServicosBasesImplementados<Estudiante, String, EstudianteRepositorio> {

	private final EstudianteRepositorio estudianteRepositorio;

	public EstudianteServicio(EstudianteRepositorio estudianteRepositorio) {
		super();
		this.estudianteRepositorio = estudianteRepositorio;
	}
	
	
	public List<Estudiante> obtenerTodosLosEstudiantes(){
		return estudianteRepositorio.findAll();
	}
	
	public Estudiante guardar(Estudiante e) {
	    return estudianteRepositorio.save(e);
	}


	public Optional<Estudiante> findById(String dni) {
		return estudianteRepositorio.findById(dni);
	}

}
