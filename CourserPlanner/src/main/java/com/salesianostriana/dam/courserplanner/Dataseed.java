package com.salesianostriana.dam.courserplanner;

import java.time.Duration;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Rol;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio;
import com.salesianostriana.dam.courserplanner.seguridad.ConfiguradorCodificadorContrasenas;

import jakarta.annotation.PostConstruct;

@Component
public class Dataseed {

	
	private final CursoRepositorio cursoRepositorio;
	private final UsuarioRepositorio usuarioRepositorio;
	private final ConfiguradorCodificadorContrasenas codi;

	

	public Dataseed( CursoRepositorio cursoRepositorio, UsuarioRepositorio usuarioRepositorio,
			ConfiguradorCodificadorContrasenas codi) {
		super();
		
		this.cursoRepositorio = cursoRepositorio;
		this.usuarioRepositorio = usuarioRepositorio;
		this.codi = codi;
	}

	@PostConstruct
	public void init() {

		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("EL DATASEED SE ESTÁ EJECUTANDO");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		if (usuarioRepositorio.count() == 0) {
			System.out.println("La base de datos está vacía, procediendo a insertar...");
			Instructor admin = new Instructor();
			admin.setDni("12345678A");
			admin.setFoto("https://dinastiacachorros.com.co/wp-content/uploads/2024/05/Img-Dinastia-del-Cachorro-Home-Razas-Husky.png.webp");
			admin.setUsername("admin");
			admin.setApellidos("admin");
			admin.setEmail("admin@admin.com");
			admin.setTelefono("12343423");
			admin.setFechaNacimiento(LocalDate.of(1955, 02, 26));
			admin.setPais("España");
			admin.setLocalidad("Sevilla");
			admin.setEspecialidad("Programación");
			admin.setValoracionMedia(3.5);
			admin.setPassword(codi.CodificadorContrasenas().encode("admin"));
			admin.setRol(Rol.ADMIN);
			usuarioRepositorio.save(admin);

			Estudiante user = new Estudiante();
			user.setDni("87654321B");
			user.setFoto("foto");
			user.setUsername("user");
			user.setApellidos("user");
			user.setEmail("usser@user.com");
			user.setTelefono("12343423");
			user.setFechaNacimiento(LocalDate.of(1922, 12, 16));
			user.setPais("España");
			user.setLocalidad("Sevilla");
			user.setNivelExperiencia("Intermedio");
			user.setPassword(codi.CodificadorContrasenas().encode("user"));
			user.setRol(Rol.USER);
			usuarioRepositorio.save(user);

			Curso cursoPrueba = Curso.builder()
				    .titulo("Curso de Prueba")
				    .categoria("Programación")
				    .descripcion("Descripción de prueba para verificar que el listado funciona.")
				    .fotoCurso("https://via.placeholder.com/150")
				    .precio(99.99)
				    .plazasMaximas(20)
				    .duracionHoras(Duration.ofHours(2).plusMinutes(30)) 
				    .instructor(admin) 
				    .build();

				cursoRepositorio.save(cursoPrueba);
			

		}

	}
}