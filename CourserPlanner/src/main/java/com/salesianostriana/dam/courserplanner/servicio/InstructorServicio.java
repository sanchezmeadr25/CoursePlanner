package com.salesianostriana.dam.courserplanner.servicio;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.repositorio.InstructorRepositorio;
import com.salesianostriana.dam.courserplanner.serviciobase.ServicosBasesImplementados;

@Service 
public class InstructorServicio extends ServicosBasesImplementados<Instructor,String,InstructorRepositorio> {

	public InstructorServicio(InstructorRepositorio repositorio) {
		super(repositorio);
		// TODO Auto-generated constructor stub
	}

}
