package com.salesianostriana.dam.courserplanner.repositorio;


import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Inscripcion;
import com.salesianostriana.dam.courserplanner.modelo.InscripcionPK;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InscripcionRepositorio extends JpaRepository<Inscripcion, InscripcionPK> {

	List<Inscripcion> findAllByEstudianteDni(String dniEstudiante);

}
