package com.salesianostriana.dam.courserplanner.servicio;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Inscripcion;
import com.salesianostriana.dam.courserplanner.modelo.InscripcionPK;
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.EstudianteRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.InscripcionRepositorio;
import com.salesianostriana.dam.courserplanner.serviciobase.ServicosBasesImplementados;

@Service
public class InscripcionServicio
		extends ServicosBasesImplementados<Inscripcion, InscripcionPK, InscripcionRepositorio> {

	private final EstudianteRepositorio estudianteRepositorio;
	private final CursoRepositorio cursoRepositorio;

	public InscripcionServicio(InscripcionRepositorio inscripcionRepositorio,
			EstudianteRepositorio estudianteRepositorio, CursoRepositorio cursoRepositorio) {
		
	
		super(inscripcionRepositorio); 

		this.estudianteRepositorio = estudianteRepositorio;
		this.cursoRepositorio = cursoRepositorio;
	}

	public Inscripcion registrarInscripcion(Inscripcion inscripcion) {

	
		if (repositorio.existsById(inscripcion.getInscripcionPK())) {
			throw new RuntimeException("El estudiante ya está inscrito en este curso.");
		}

		Estudiante est = estudianteRepositorio.findById(inscripcion.getInscripcionPK().getEstudianteDni())
				.orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

		Curso cur = cursoRepositorio.findById(inscripcion.getInscripcionPK().getCursoId())
				.orElseThrow(() -> new RuntimeException("Curso no encontrado"));

		inscripcion.setEstudiante(est);
		inscripcion.setCurso(cur);
		inscripcion.setFechaInscripcion(LocalDateTime.now());
		inscripcion.setProgreso(0.0);

		return repositorio.save(inscripcion);
	}
}