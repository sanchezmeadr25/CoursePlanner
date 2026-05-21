package com.salesianostriana.dam.courserplanner;



import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio;
import com.salesianostriana.dam.courserplanner.seguridad.ConfiguradorCodificadorContrasenas;

import jakarta.annotation.PostConstruct;

public class Dataseed {

	private final UsuarioRepositorio usuarioRepositorio;
	private final ConfiguradorCodificadorContrasenas codi;
	
	@PostConstruct
	public void init() {
		
		Usuario user = Usuario.builder()
				.email("user@user.com")
				.username("user")
				
		
		repo.save(user);
}
