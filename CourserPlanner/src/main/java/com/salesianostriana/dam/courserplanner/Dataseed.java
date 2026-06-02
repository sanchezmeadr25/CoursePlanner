package com.salesianostriana.dam.courserplanner;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.EstadoInscripcion;
import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Inscripcion;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Rol;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.InscripcionRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio;
import com.salesianostriana.dam.courserplanner.seguridad.ConfiguradorCodificadorContrasenas;

import jakarta.annotation.PostConstruct;

@Component
public class Dataseed {

	
	private final CursoRepositorio cursoRepositorio;
	private final UsuarioRepositorio usuarioRepositorio;
	private final ConfiguradorCodificadorContrasenas codi;
	private final InscripcionRepositorio inscripcionRepositorio; 

	public Dataseed(CursoRepositorio cursoRepositorio, UsuarioRepositorio usuarioRepositorio,
	                ConfiguradorCodificadorContrasenas codi, InscripcionRepositorio inscripcionRepositorio) {
	    this.cursoRepositorio = cursoRepositorio;
	    this.usuarioRepositorio = usuarioRepositorio;
	    this.codi = codi;
	    this.inscripcionRepositorio = inscripcionRepositorio; 
	}

	@PostConstruct
	public void init() {

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
		
			
			Instructor admin2 = new Instructor();
			admin2.setDni("11111111A");
			admin2.setFoto("https://via.placeholder.com/150");
			admin2.setUsername("ana_instructor");
			admin2.setApellidos("García López");
			admin2.setEmail("ana@instructor.com");
			admin2.setTelefono("600111222");
			admin2.setFechaNacimiento(LocalDate.of(1985, 4, 12));
			admin2.setPais("España");
			admin2.setLocalidad("Madrid");
			admin2.setEspecialidad("Diseño UI/UX");
			admin2.setValoracionMedia(4.8);
			admin2.setPassword(codi.CodificadorContrasenas().encode("admin2"));
			admin2.setRol(Rol.ADMIN);
			usuarioRepositorio.save(admin2);

			Instructor admin3 = new Instructor();
			admin3.setDni("22222222B");
			admin3.setFoto("https://via.placeholder.com/150");
			admin3.setUsername("carlos_instructor");
			admin3.setApellidos("Martín Gómez");
			admin3.setEmail("carlos@instructor.com");
			admin3.setTelefono("600333444");
			admin3.setFechaNacimiento(LocalDate.of(1990, 9, 23));
			admin3.setPais("España");
			admin3.setLocalidad("Barcelona");
			admin3.setEspecialidad("Ciberseguridad");
			admin3.setValoracionMedia(4.5);
			admin3.setPassword(codi.CodificadorContrasenas().encode("admin3"));
			admin3.setRol(Rol.ADMIN);
			usuarioRepositorio.save(admin3);
			
			Instructor admin4 = new Instructor();
			admin4.setDni("33333333C");
			admin4.setFoto("https://via.placeholder.com/150");
			admin4.setUsername("lucia_instructor");
			admin4.setApellidos("Sánchez Ruiz");
			admin4.setEmail("lucia@instructor.com");
			admin4.setTelefono("600555666");
			admin4.setFechaNacimiento(LocalDate.of(1978, 11, 5));
			admin4.setPais("España");
			admin4.setLocalidad("Valencia");
			admin4.setEspecialidad("Big Data");
			admin4.setValoracionMedia(4.9);
			admin4.setPassword(codi.CodificadorContrasenas().encode("admin4"));
			admin4.setRol(Rol.ADMIN);
			usuarioRepositorio.save(admin4);
	
			

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
			
			Estudiante user2 = new Estudiante();
			user2.setDni("44444444D");
			user2.setFoto("https://via.placeholder.com/150");
			user2.setUsername("juan_alumno");
			user2.setApellidos("Pérez Fernández");
			user2.setEmail("juan@estudiante.com");
			user2.setTelefono("654987321");
			user2.setFechaNacimiento(LocalDate.of(1998, 5, 20));
			user2.setPais("España");
			user2.setLocalidad("Málaga");
			user2.setNivelExperiencia("Principiante");
			user2.setPassword(codi.CodificadorContrasenas().encode("user2"));
			user2.setRol(Rol.USER);
			usuarioRepositorio.save(user2);

			Estudiante user3 = new Estudiante();
			user3.setDni("55555555E");
			user3.setFoto("https://via.placeholder.com/150");
			user3.setUsername("marta_alumno");
			user3.setApellidos("Jiménez Díaz");
			user3.setEmail("marta@estudiante.com");
			user3.setTelefono("654123789");
			user3.setFechaNacimiento(LocalDate.of(2001, 1, 30));
			user3.setPais("España");
			user3.setLocalidad("Cádiz");
			user3.setNivelExperiencia("Avanzado");
			user3.setPassword(codi.CodificadorContrasenas().encode("user3"));
			user3.setRol(Rol.USER);
			usuarioRepositorio.save(user3);

			Estudiante user4 = new Estudiante();
			user4.setDni("66666666F");
			user4.setFoto("https://via.placeholder.com/150");
			user4.setUsername("pablo_alumno");
			user4.setApellidos("Torres Navarro");
			user4.setEmail("pablo@estudiante.com");
			user4.setTelefono("611223344");
			user4.setFechaNacimiento(LocalDate.of(1995, 7, 14));
			user4.setPais("España");
			user4.setLocalidad("Granada");
			user4.setNivelExperiencia("Intermedio");
			user4.setPassword(codi.CodificadorContrasenas().encode("user4"));
			user4.setRol(Rol.USER);
			usuarioRepositorio.save(user4);

			Curso cursoPrueba = Curso.builder()
	                .titulo("Curso de Prueba")
	                .categoria("Programación")
	                .descripcion("Descripción de prueba...")
	                .fotoCurso("https://cdn-imgix.headout.com/media/images/c9db3cea62133b6a6bb70597326b4a34-388-dubai-img-worlds-of-adventure-tickets-01.jpg?auto=compress%2Cformat&w=1222.3999999999999&h=687.6&q=90&ar=16%3A9&crop=faces&fit=crop")
	                .precio(99.99)
	                .descuento(0.0)
	                .plazasMaximas(20)
	                .duracionHoras(Duration.ofHours(2).plusMinutes(30)) 
	                .instructor(admin) 
	                .build();
	        cursoRepositorio.save(cursoPrueba);

	        Curso cursoDiseno = Curso.builder()
	                .titulo("Introducción a Figma y UI/UX")
	                .categoria("Diseño UI/UX")
	                .descripcion("Aprende a diseñar interfaces...")
	                .fotoCurso("https://talentoformacion.com/wp-content/uploads/2026/04/figma.png")
	                .precio(49.99)
	                .descuento(20.0) 
	                .plazasMaximas(15)
	                .duracionHoras(Duration.ofHours(15))
	                .instructor(admin) 
	                .build();
	        cursoRepositorio.save(cursoDiseno);

	        Curso cursoHacking = Curso.builder()
	                .titulo("Hacking Ético y Seguridad Web")
	                .categoria("Ciberseguridad")
	                .descripcion("Descubre las principales vulnerabilidades...")
	                .fotoCurso("https://inforges.es/wp-content/uploads/2024/06/que-es-el-hacking-etico-y-como-se-lleva-a-cabo-inforges.jpg")
	                .precio(149.99)
	                .descuento(10.0)
	                .plazasMaximas(10)
	                .duracionHoras(Duration.ofHours(40))
	                .instructor(admin) 
	                .build();
	        cursoRepositorio.save(cursoHacking);

	        Curso cursoPython = Curso.builder()
	                .titulo("Python para Ciencia de Datos")
	                .categoria("Big Data")
	                .descripcion("Domina Pandas, NumPy y Matplotlib...")
	                .fotoCurso("https://miro.medium.com/1*3IcLSFuT8PQg4cUBaRXH1A.png")
	                .precio(120.00)
	                .descuento(0.0) 
	                .plazasMaximas(25)
	                .duracionHoras(Duration.ofHours(25).plusMinutes(45))
	                .instructor(admin) 
	                .build();
	        cursoRepositorio.save(cursoPython);
		
	        
	        Inscripcion ins1 = new Inscripcion();
	        ins1.setEstudiante(user);
	        ins1.setCurso(cursoPrueba); 
	        ins1.setEstado(EstadoInscripcion.EN_CURSO);
	        ins1.setProgreso(24);
	        ins1.setFechaInscripcion(LocalDateTime.now());
	        inscripcionRepositorio.save(ins1);

	        Inscripcion ins2 = new Inscripcion();
	        ins2.setEstudiante(user2);
	        ins2.setCurso(cursoPrueba);
	        ins2.setEstado(EstadoInscripcion.COMPLETADO);
	        ins2.setProgreso(100);
	        ins2.setFechaInscripcion(LocalDateTime.now());
	        inscripcionRepositorio.save(ins2);

	        Inscripcion ins3 = new Inscripcion();
	        ins3.setEstudiante(user3);
	        ins3.setCurso(cursoHacking);
	        ins3.setEstado(EstadoInscripcion.PENDIENTE);
	        ins3.setProgreso(0);
	        ins3.setFechaInscripcion(LocalDateTime.now());
	        inscripcionRepositorio.save(ins3);

	}
}