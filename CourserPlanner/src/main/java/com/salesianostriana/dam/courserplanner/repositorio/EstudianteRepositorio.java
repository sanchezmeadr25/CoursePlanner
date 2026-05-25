package com.salesianostriana.dam.courserplanner.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Inscripcion;

@Repository
public interface EstudianteRepositorio extends JpaRepository<Estudiante, String>{

	
	
}
