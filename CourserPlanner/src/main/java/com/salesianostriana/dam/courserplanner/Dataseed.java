package com.salesianostriana.dam.courserplanner;



import java.time.LocalDate;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Rol;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio;
import com.salesianostriana.dam.courserplanner.seguridad.ConfiguradorCodificadorContrasenas;

import jakarta.annotation.PostConstruct;

public class Dataseed {

	private final UsuarioRepositorio usuarioRepositorio;
	private final ConfiguradorCodificadorContrasenas codi;
	
	
	public Dataseed(UsuarioRepositorio usuarioRepositorio, ConfiguradorCodificadorContrasenas codi) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
		this.codi = codi;
	}

	@PostConstruct
	public void init() {
		
		Instructor admin= new Instructor();
		admin.setDni("12345678A");
		admin.setFotoPerfil("foto");
		admin.setUsername("admin");
		admin.setApellidos("admin");
		admin.setEmail("admin@admin.com");
		admin.setTelefono("12343423");
		admin.setFechaNacimiento(LocalDate.of(1955, 02, 26));
		admin.setPais("España");
		admin.setLocalidad("Sevilla");
		admin.setEspecialidad("Programación");
		admin.setValoracionMedia(3.5);
		admin.setPassword("admin");
		admin.setRol(Rol.ADMIN);
		usuarioRepositorio.save(admin);
		
		Estudiante user= new Estudiante();
		user.setDni("87654321B");
		user.setFotoPerfil("foto");
		user.setUsername("user");
		user.setApellidos("user");
		user.setEmail("usser@user.com");
		user.setTelefono("12343423");
		user.setFechaNacimiento(LocalDate.of(1922, 12, 16));
		user.setPais("España");
		user.setLocalidad("Sevilla");
		user.setNivelExperiencia("Intermedio");
		user.setPassword("user");
		user.setRol(Rol.USER);
		usuarioRepositorio.save(user);
		
	}
		
	
}
