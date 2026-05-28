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
import org.springframework.web.bind.annotation.PathVariable;
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

	//Formulario crear Curso
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
	
	//Listado de cursos admin
	@GetMapping("/misCursos")
	public String listarMisCursos(Model model, Principal principal) {
	    
	    model.addAttribute("listaCurso", usuarioRepositorio.findByUsername(principal.getName())
	            .map(instructor -> cursoServicio.buscarCursoPorInstructor(instructor.getDni()))
	            .orElse(Collections.emptyList()));
	            
	    return "admin/listaCursos";
	}
	
	@GetMapping("admin/misCursos")
	public String listaarMisCursos(Model model, Principal principal) {
	    
	    model.addAttribute("listaCurso", usuarioRepositorio.findByUsername(principal.getName())
	            .map(instructor -> cursoServicio.buscarCursoPorInstructor(instructor.getDni()))
	            .orElse(Collections.emptyList()));
	            
	    return "admin/listaCursos";
	}
	
	//Lista de todos los cursos existente para los estudiantes
	@GetMapping("/catalogo")
	public String listarTodosLosCursos(Model model) {
		model.addAttribute("cursos", cursoServicio.buscarTodos());
		return "listaCursosEstudiante";

	}
	
	
	//Formulario editar curso
	@GetMapping("admin/editarCurso/{id}")
	public String mostrarFormularioEdicionCurso(@PathVariable("id") Long id, Model model ) {
		
		Optional<Curso>curso = cursoServicio.findById(id);
		
		if(curso.isPresent()) {
			model.addAttribute("curso", curso.get());
			return "formularioCurso";
		}else {
			return "admin:/misCursos";
		}	
	}
	
	@PostMapping("admin/editarCurso/submit")
	public String procesarFormularioEdicionCurso(@ModelAttribute("curso") Curso curso) {
		
		cursoServicio.editar(curso);
		return "redirect:/admin/misCursos";
	}
	
	
	
	//Lista de cursos inscritos el estudiante
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
