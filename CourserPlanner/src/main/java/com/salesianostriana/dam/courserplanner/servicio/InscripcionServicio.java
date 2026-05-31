package com.salesianostriana.dam.courserplanner.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

	public InscripcionServicio(InscripcionRepositorio repositorio,
			EstudianteRepositorio estudianteRepositorio, CursoRepositorio cursoRepositorio) {
		super(repositorio);
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

	// Primero cogemos la lista de las inscripciones que tiene el estudiante, luego
	// la transformamos en una lista de cursos y sacamos el cirso de la inscripcion
	// y hacemos una lista con los curso sacaado
	public List<Curso> buscarCursosPorEstudiante(String dniEstudiante) {
		List<Inscripcion> inscripciones = repositorio.findAllByEstudianteDni(dniEstudiante);
		return inscripciones.stream().map(i -> i.getCurso()).collect(Collectors.toList());
	}

	public Inscripcion findById(InscripcionPK inscripcionPK) {
		// TODO Auto-generated method stub
		return repositorio.findById(inscripcionPK)
                .orElse(null);
	}
}