package com.salesianostriana.dam.courserplanner.repositorio;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {

	List<Curso> findByInstructor_Dni(String dni);
	
	int countByInstructor_Dni(String dni);
	
}
