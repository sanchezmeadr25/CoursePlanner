package com.salesianostriana.dam.courserplanner.controlador;

import java.security.Principal;
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
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio;
import com.salesianostriana.dam.courserplanner.servicio.CursoServicio;
import com.salesianostriana.dam.courserplanner.servicio.InstructorServicio;

@Controller
public class CursoControlador {

	private final CursoServicio cursoServicio;
	private final InstructorRepositorio instructorRepositorio;
	private final InstructorServicio instructorServicio;
	private final UsuarioRepositorio usuarioRepositorio;
	
	public CursoControlador(CursoServicio cursoServicio, InstructorRepositorio instructorRepositorio,InstructorServicio instructorServicio,UsuarioRepositorio usuarioRepositorio) {
		super();
		this.cursoServicio = cursoServicio;
		this.instructorRepositorio = instructorRepositorio;
		this.instructorServicio = instructorServicio;
		this.usuarioRepositorio = usuarioRepositorio;
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
	
	@GetMapping("admin/misCursos")
	public String listarMisCursos(Model model, Principal principal) {
	    
		String username = principal.getName();
	    
	    // 1. Buscamos el Optional
	    Optional<Usuario> usuarioOpt = usuarioRepositorio.findByUsername(username);
	    
	    Usuario usuario = usuarioOpt.get();

	    model.addAttribute("listaCurso", cursoServicio.findCursosByInstructor(usuario.getDni()));
	    
	    return "admin/listaCursos";
	    	
	    }
	
	
	
	
	
	
	
	
	
	
	
	
}
