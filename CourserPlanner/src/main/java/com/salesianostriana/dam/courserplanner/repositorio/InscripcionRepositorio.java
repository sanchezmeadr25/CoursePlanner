package com.salesianostriana.dam.courserplanner.repositorio;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Inscripcion;
import com.salesianostriana.dam.courserplanner.modelo.InscripcionPK;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepositorio extends JpaRepository<Inscripcion, InscripcionPK> {

	List<Inscripcion> findAllByEstudianteDni(String dniEstudiante);

	// Contar estudiantes únicos inscritos en cursos de este instructor
	@Query("SELECT COUNT(DISTINCT i.estudiante.dni) FROM Inscripcion i WHERE i.curso.instructor.dni = :dni")
	long countEstudiantesPorInstructor(@Param("dni") String dni);

	// Contar cuántas valoraciones han hecho los alumnos en los cursos del
	// instructor
	@Query("SELECT COUNT(i) FROM Inscripcion i WHERE i.curso.instructor.dni = :dni AND i.valoracion > 0")
	long countValoracionesPorInstructor(@Param("dni") String dni);

	// Contamos los estudiantes que tiene el instructor
	@Query("SELECT COUNT(i) FROM Inscripcion i WHERE i.curso.instructor.dni = :dni")
	int contarEstudiantesPorInstructor(@Param("dni") String dni);

	// Contamos los cursos a lo s que esta inscrito el estudiante
	@Query("SELECT COUNT(i) FROM Inscripcion i WHERE i.estudiante.dni = :dni")
	long countCursosInscritosPorEstudiante(@Param("dni") String dni);

	// Contar cuántas valoraciones han hecho los alumnos
	@Query("SELECT COUNT(i) FROM Inscripcion i WHERE i.estudiante.dni = :dni AND i.valoracion > 0")
	long countValoracionesPorEstudiante(@Param("dni") String dni);
	
	//Buscamos la inscripcion
	@Query("SELECT i FROM Inscripcion i WHERE i.estudiante.dni = :dni AND i.curso.id = :id")
    Optional<Inscripcion> findByEstudianteDniAndCursoId(@Param("dni") String estudianteDni, @Param("id") Long cursoId);
	
	//Buscamos el curso
	@Query("SELECT i FROM Inscripcion i WHERE i.curso.id = :id")
    List<Inscripcion> findByCursoId(@Param("id") Long cursoId);
	
	//Calculamos la valoracion Mdia del progesor
	@Query("SELECT AVG(i.valoracion) FROM Inscripcion i WHERE i.curso.instructor.dni = :instructorDni AND i.valoracion > 0")
	Double calcularMediaValoracionInstructor(@Param("instructorDni") String instructorDni);

	@Query("SELECT i FROM Inscripcion i JOIN FETCH i.curso JOIN FETCH i.estudiante WHERE i.curso.instructor.dni = :dni AND i.valoracion > 0 ORDER BY i.fechaInscripcion DESC")
	List<Inscripcion> findValoracionesRecientesPorInstructor(@Param("dni") String dni);
}
