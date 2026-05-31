package com.salesianostriana.dam.courserplanner.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.courserplanner.modelo.Usuario;


public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

	public List<Usuario> findByDni(String dni);

	Optional<Usuario> findByUsername(String username);
	
}
