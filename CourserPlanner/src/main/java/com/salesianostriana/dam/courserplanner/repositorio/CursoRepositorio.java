package com.salesianostriana.dam.courserplanner.repositorio;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {

	
	List<Curso> findByInstructor_Dni(String dni);
	
	int countByInstructor_Dni(String dni);
	
	@Query("SELECT c FROM Curso c LEFT JOIN FETCH c.instructor LEFT JOIN FETCH c.listaInscripciones WHERE c.id = :id")
		Optional<Curso> buscarInscripcionesyEstudiantes(@Param("id") Long id);

}
