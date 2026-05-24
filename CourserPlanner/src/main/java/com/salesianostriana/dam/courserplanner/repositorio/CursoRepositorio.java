package com.salesianostriana.dam.courserplanner.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.courserplanner.modelo.Curso;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {

}
