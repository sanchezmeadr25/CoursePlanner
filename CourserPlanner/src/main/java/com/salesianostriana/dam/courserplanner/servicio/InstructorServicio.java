package com.salesianostriana.dam.courserplanner.servicio;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.repositorio.InstructorRepositorio;
import com.salesianostriana.dam.courserplanner.serviciobase.ServicosBasesImplementados;

@Service 
public class InstructorServicio extends ServicosBasesImplementados<Instructor,String,InstructorRepositorio> {

	private final InstructorRepositorio instructorRepositorio;

	public InstructorServicio(InstructorRepositorio instructorRepositorio) {
		super();
		this.instructorRepositorio = instructorRepositorio;
	}
	
	public Instructor crearInstructor(Instructor instructor) {
        return instructorRepositorio.save(instructor);
    }
	
	/*public int calcularTotalCursos(Instructor instructor) {
        return instructor.getListaCursos().size();
    }
	
	
	public double calcularValoracionMedia(Instructor instructor) {
        return instructor.getListaCursos().stream()
                .mapToDouble(Curso::getValoracion)
                .average()
                .orElse(0.0);
    }*/
}
