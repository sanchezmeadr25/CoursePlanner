package com.salesianostriana.dam.courserplanner.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.courserplanner.modelo.Curso;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {

   //Buscamos los cursos que tiene un profesor
    List<Curso> findByInstructor_Dni(String dni);
    
   //Vemos cuantos cursos tiene un profesor
    @Query("SELECT COUNT(c) FROM Curso c WHERE c.instructor.dni = :dni")
    int cantidadCursosInstructor(String dni);
    
    //Buscamos todam la informacion que tiene un curso
    @Query("SELECT c FROM Curso c LEFT JOIN FETCH c.instructor LEFT JOIN FETCH c.listaInscripciones WHERE c.id = :id")
    Optional<Curso> buscarInscripcionesyEstudiantes(@Param("id") Long id);

   //Vemos el curso que tiene mas alumnos inscritos
    @Query("SELECT c FROM Curso c JOIN c.listaInscripciones i GROUP BY c.id ORDER BY COUNT(i) DESC")
    List<Curso> findCursosMasDemandados();

   //Buscamos cursos entre un minimo y un maximos de precio
    @Query("SELECT c FROM Curso c WHERE c.precio BETWEEN :min AND :max")
    List<Curso> findByPrecioBetween(@Param("min") double min, @Param("max") double max);

 // Contar cuántos estudiantes únicos tiene un instructor en todos sus cursos
    @Query("SELECT COUNT(DISTINCT i.estudiante.dni) FROM Inscripcion i WHERE i.curso.instructor.dni = :dni")
    int countEstudiantesPorInstructor(@Param("dni") String dni);

    // Calcular la media de las valoraciones de las inscripciones del instructor
    @Query("SELECT COALESCE(AVG(i.valoracion), 0) FROM Inscripcion i WHERE i.curso.instructor.dni = :dni AND i.valoracion > 0")
    double mediaValoracionInstructor(@Param("dni") String dni);

    // Contar cuántas valoraciones (no nulas) han dejado los alumnos
    @Query("SELECT COUNT(i) FROM Inscripcion i WHERE i.curso.instructor.dni = :dni AND i.valoracion > 0")
    int countValoracionesPorInstructor(@Param("dni") String dni);
    
    
} 
