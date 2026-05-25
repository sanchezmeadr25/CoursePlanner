package com.salesianostriana.dam.courserplanner.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {

	List<Curso> findByInstructor(Optional<Instructor> instructor);
	
}
