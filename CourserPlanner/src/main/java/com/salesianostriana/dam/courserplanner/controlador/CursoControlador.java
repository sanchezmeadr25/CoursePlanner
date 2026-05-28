package com.salesianostriana.dam.courserplanner.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.InstructorRepositorio;
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

	private void cargarUsuario(Model model, Principal principal) {
		if (principal != null) {
			usuarioRepositorio.findByUsername(principal.getName())
					.ifPresent(usuario -> model.addAttribute("usuario", usuario));
		}
	}

	
	@GetMapping("/crearCurso")
	public String mostrarFormulario(Model model, Principal principal) {
		cargarUsuario(model, principal);
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

	@GetMapping("/admin/editarCurso/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model, Principal principal) {
		cargarUsuario(model, principal);
		Optional<Curso> curso = cursoServicio.buscarPorId(id);

		if (curso.isPresent()) {
			Curso cursoAEditar = curso.get();

			if (cursoAEditar.getDuracionHoras() != null) {
				cursoAEditar.setHorasForm((int) cursoAEditar.getDuracionHoras().toHours());
				cursoAEditar.setMinutosForm(cursoAEditar.getDuracionHoras().toMinutesPart());
			}

			model.addAttribute("curso", cursoAEditar);
			model.addAttribute("listaInstructores", instructorRepositorio.findAll());
			return "formularioCurso";
		}

		return "redirect:/admin/misCursos";
	}

	@PostMapping("/admin/editarCurso/submit")
	public String procesarFormularioEdicion(@ModelAttribute Curso cursoEditado) {
		Optional<Curso> cursoOriginal = cursoServicio.buscarPorId(cursoEditado.getId());

		if (cursoOriginal.isPresent()) {
			Curso curso = cursoOriginal.get();
			curso.setTitulo(cursoEditado.getTitulo());
			curso.setCategoria(cursoEditado.getCategoria());
			curso.setPrecio(cursoEditado.getPrecio());
			curso.setPlazasMaximas(cursoEditado.getPlazasMaximas());
			curso.setFotoCurso(cursoEditado.getFotoCurso());
			curso.setDescripcion(cursoEditado.getDescripcion());
			curso.setHorasForm(cursoEditado.getHorasForm());
			curso.setMinutosForm(cursoEditado.getMinutosForm());

			cursoServicio.editar(curso);
		}

		return "redirect:/admin/misCursos";
	}
	
	@GetMapping("admin/misCursos")
	public String listarMisCursos(Model model, Principal principal) {
		cargarUsuario(model, principal);
	    
	    model.addAttribute("listaCurso", usuarioRepositorio.findByUsername(principal.getName())
	            .map(instructor -> cursoServicio.buscarCursoPorInstructor(instructor.getDni()))
	            .orElse(Collections.emptyList()));
	            
	    return "admin/listaCursos";
	}
	
	@GetMapping("/catalogo")
	public String listarTodosLosCursos(Model model, Principal principal) {
		cargarUsuario(model, principal);
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
	    
	    model.addAttribute("usuario", usuario);
	    model.addAttribute("misCursos", (misCursos != null) ? misCursos : new ArrayList<Curso>());
	    
	    return "misCursosInscritos"; 
	}
	
	
	
	
	
	
	
	
}
