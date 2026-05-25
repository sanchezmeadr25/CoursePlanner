package com.salesianostriana.dam.courserplanner.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.courserplanner.modelo.Instructor;

public interface InstructorRepositorio extends JpaRepository<Instructor, String> {

}
