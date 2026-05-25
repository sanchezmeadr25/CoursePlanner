package com.salesianostriana.dam.courserplanner.controlador;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.InstructorRepositorio;
import com.salesianostriana.dam.courserplanner.servicio.CursoServicio;
import com.salesianostriana.dam.courserplanner.servicio.InstructorServicio;

@Controller
public class CursoControlador {

	private final CursoServicio cursoServicio;
	private final InstructorRepositorio instructorRepositorio;
	private final InstructorServicio instructorServicio;
	
	
	public CursoControlador(CursoServicio cursoServicio, InstructorRepositorio instructorRepositorio,InstructorServicio instructorServicio) {
		super();
		this.cursoServicio = cursoServicio;
		this.instructorRepositorio = instructorRepositorio;
		this.instructorServicio = instructorServicio;
	}
	
	
	@GetMapping("/crearCurso")
	public String mostrarFormulario(Model model) {
		model.addAttribute("curso",new Curso());
		model.addAttribute("listaInstructores",instructorRepositorio.findAll());
		return "formularioCurso";
	}
	
	@PostMapping("/crearCurso/submit")
	public String guardarCurso(@ModelAttribute("curso") Curso curso) {
		cursoServicio.guardar(curso);
		return "redirect:/";
	}
	
	@GetMapping("/misCursos")
	public String listarMisCursos(Model model,@AuthenticationPrincipal Usuario usuario) {
		Optional<Instructor> instructor = instructorServicio.buscarPorId(usuario.getDni());
				
		model.addAttribute("listaCurso",cursoServicio.findCursosByInstructor(instructor));
		
		return "admin/listaCursos";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
