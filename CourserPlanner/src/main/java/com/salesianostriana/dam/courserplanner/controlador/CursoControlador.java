package com.salesianostriana.dam.courserplanner.controlador;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.InstructorRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio;
import com.salesianostriana.dam.courserplanner.servicio.CursoServicio;
import com.salesianostriana.dam.courserplanner.servicio.InscripcionServicio;
import com.salesianostriana.dam.courserplanner.servicio.InstructorServicio;

@Controller
public class CursoControlador {

	private final InscripcionServicio inscripcionServicio;
	private final CursoRepositorio cursoRepositorio;
	private final CursoServicio cursoServicio;
	private final InstructorRepositorio instructorRepositorio;
	private final InstructorServicio instructorServicio;
	private final UsuarioRepositorio usuarioRepositorio;
	
	
	public CursoControlador(InscripcionServicio inscripcionServicio, CursoRepositorio cursoRepositorio,
			CursoServicio cursoServicio, InstructorRepositorio instructorRepositorio,
			InstructorServicio instructorServicio, UsuarioRepositorio usuarioRepositorio) {
		super();
		this.inscripcionServicio = inscripcionServicio;
		this.cursoRepositorio = cursoRepositorio;
		this.cursoServicio = cursoServicio;
		this.instructorRepositorio = instructorRepositorio;
		this.instructorServicio = instructorServicio;
		this.usuarioRepositorio = usuarioRepositorio;
	}

	
	@GetMapping("/crearCurso")
	public String mostrarFormulario(Model model) {
	    model.addAttribute("curso", new Curso());
	    model.addAttribute("listaInstructores", instructorRepositorio.findAll());
	    return "formularioCurso"; 
	}

	@PostMapping("/crearCurso/submit")
	public String guardarCurso(@ModelAttribute Curso curso) {
	    
	    List<Instructor> lista = instructorRepositorio.findAll();
	    if (!lista.isEmpty()) {
	        curso.setInstructor(lista.get(0)); 
	    }
	    
	    cursoRepositorio.save(curso);
	    return "redirect:/admin/misCursos";
	}
	
	@GetMapping("admin/misCursos")
	public String listarMisCursos(Model model, Principal principal) {
	    
	    model.addAttribute("listaCurso", usuarioRepositorio.findByUsername(principal.getName())
	            .map(instructor -> cursoServicio.buscarCursoPorInstructor(instructor.getDni()))
	            .orElse(Collections.emptyList()));
	            
	    return "admin/listaCursos";
	}
	
	@GetMapping("/catalogo")
	public String listarTodosLosCursos(Model model) {
		model.addAttribute("cursos", cursoServicio.buscarTodos());
		return "listaCursosEstudiante";

	}
	
	
	@GetMapping("/misCursosInscritos")
	public String listarMisCursosInscritos(Model model, Principal principal) {
	    String username = principal.getName();
	    Usuario usuario = usuarioRepositorio.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	    
	    List<Curso> misCursos = inscripcionServicio.buscarCursosPorEstudiante(usuario.getDni());
	    
	  
	    if (misCursos != null) {
	        misCursos.removeIf(Objects::isNull);
	    }
	    
	    model.addAttribute("misCursos", (misCursos != null) ? misCursos : new ArrayList<Curso>());
	    
	    return "misCursosInscritos"; 
	}
	
	
	
	
	
	
	
	
}
