package com.salesianostriana.dam.courserplanner.servicio;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.repositorio.InstructorRepositorio;
import com.salesianostriana.dam.courserplanner.serviciobase.ServicosBasesImplementados;

@Service 
public class InstructorServicio extends ServicosBasesImplementados<Instructor,String,InstructorRepositorio> {

	private final InstructorRepositorio instructorRepositorio;

	public InstructorServicio(InstructorRepositorio instructorRepositorio) {
		super(instructorRepositorio);
		this.instructorRepositorio = instructorRepositorio;
	}
	
	public Instructor crearInstructor(Instructor instructor) {
        return instructorRepositorio.save(instructor);
    }
	// Calcula cuántos cursos tiene el instructor
    public int calcularTotalCursos(Instructor instructor) {
        if (instructor.getCurso() == null) {
            return 0;
        }
        return instructor.getCurso().size();
    }
    
    // Calcula la valoración media basada en los cursos asociados
    public double calcularValoracionMedia(Instructor instructor) {
        if (instructor.getCurso() == null || instructor.getCurso().isEmpty()) {
            return 0.0;
        }
        
        return instructor.getCurso().stream()
                .mapToDouble(curso -> curso.getValoracion()) 
                .average()
                .orElse(0.0);
    }

	public Optional<Instructor> buscarPorId(String dni) {
	
		return instructorRepositorio.findById(dni);
	}
}
