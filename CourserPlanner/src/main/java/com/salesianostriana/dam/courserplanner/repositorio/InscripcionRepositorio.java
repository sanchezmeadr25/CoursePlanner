package com.salesianostriana.dam.courserplanner.repositorio;


import com.salesianostriana.dam.courserplanner.modelo.Inscripcion;
import com.salesianostriana.dam.courserplanner.modelo.InscripcionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InscripcionRepositorio extends JpaRepository<Inscripcion, InscripcionPK> {

}
