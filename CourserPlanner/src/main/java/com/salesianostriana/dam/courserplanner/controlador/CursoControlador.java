 package com.salesianostriana.dam.courserplanner.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.EstadoInscripcion;
import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Inscripcion;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.InscripcionRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.InstructorRepositorio;
import com.salesianostriana.dam.courserplanner.servicio.CursoServicio;
import com.salesianostriana.dam.courserplanner.servicio.InscripcionServicio;
import com.salesianostriana.dam.courserplanner.servicio.InstructorServicio;
import com.salesianostriana.dam.courserplanner.exepciones.GlobalException;

import jakarta.validation.Valid;

@Controller
public class CursoControlador {

	private final InscripcionServicio inscripcionServicio;
	private final CursoServicio cursoServicio;
	private final InstructorRepositorio instructorRepositorio;
	private final InstructorServicio instructorServicio;
	private final InscripcionRepositorio inscripcionRepositorio;

	

	public CursoControlador(InscripcionServicio inscripcionServicio, CursoServicio cursoServicio,
			InstructorRepositorio instructorRepositorio, InstructorServicio instructorServicio,
			InscripcionRepositorio inscripcionRepositorio) {
		super();
		this.inscripcionServicio = inscripcionServicio;
		this.cursoServicio = cursoServicio;
		this.instructorRepositorio = instructorRepositorio;
		this.instructorServicio = instructorServicio;
		this.inscripcionRepositorio = inscripcionRepositorio;
	}

	// Crtear cursos
	@GetMapping("/crearCurso")
	public String mostrarFormulario(Model model) {
		model.addAttribute("curso", new Curso());
		model.addAttribute("listaInstructores", instructorRepositorio.findAll());
		return "formularioCurso";
	}

	@PostMapping("/crearCurso/submit")
	public String guardarCurso(@Valid @ModelAttribute Curso curso, BindingResult bindingResult,
			@AuthenticationPrincipal Usuario usuario) {
		if (bindingResult.hasErrors()) {
			return "formularioCurso";
		}

		if (usuario instanceof Instructor) {
			curso.setInstructor((Instructor) usuario);
		}

		cursoServicio.guardar(curso);
		return "redirect:/admin/misCursos";
	}

	// Lista ed cursos que son del profesor
	@GetMapping("admin/misCursos")
	public String listarMisCursos(Model model, @AuthenticationPrincipal Usuario usuario) {
		model.addAttribute("usuario", usuario);
		model.addAttribute("listaCurso", cursoServicio.buscarCursoPorInstructor(usuario.getDni()));
		return "admin/listaCursos";
	}

	// Catalogo de cursos para el estudiante
	@GetMapping("/catalogo")
	public String listarTodosLosCursos(Model model, @AuthenticationPrincipal Usuario usuario) {
		model.addAttribute("usuario", usuario);
		model.addAttribute("cursos", cursoServicio.buscarTodos());
		return "listaCursosEstudiante";
	}

	// Lista de cursos inscritos el estuciante
	@GetMapping("/misCursosInscritos")
	public String listarMisCursosInscritos(Model model, @AuthenticationPrincipal Usuario usuario) {
		List<Curso> misCursos = inscripcionServicio.buscarCursosPorEstudiante(usuario.getDni());

		if (misCursos != null) {
			misCursos.removeIf(Objects::isNull);
		}

		model.addAttribute("misCursos", (misCursos != null) ? misCursos : new ArrayList<Curso>());
		model.addAttribute("usuario", usuario);

		return "misCursosInscritos";
	}

	// Editar CUrso
	@GetMapping("/admin/editarCurso/{id}")
	public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("curso", cursoServicio.buscarPorId(id)
				.orElseThrow(() -> new GlobalException.RecursoNoEncontradoExcepcion("ID de curso inválido: " + id)));

		model.addAttribute("listaInstructores", instructorRepositorio.findAll());

		return "formularioCurso";
	}

	@PostMapping("/admin/editarCurso/submit")
	public String guardarEdicionCurso(@Valid @ModelAttribute Curso curso, BindingResult bindingResult,
			RedirectAttributes ra) {
		if (bindingResult.hasErrors()) {
			return "formularioCurso";
		}
		cursoServicio.guardar(curso);
		ra.addFlashAttribute("mensaje", "Curso guardado correctamente");
		return "redirect:/admin/misCursos";
	}

	// Mostrar dettalles del cuso
	@GetMapping("/cursoDetalle/{id}")
	public String mostrarDetalleCurso(@PathVariable("id") Long id, Model model,
	        @AuthenticationPrincipal Usuario estudiante) { 

	    Curso curso = cursoServicio.buscarPorId(id)
	            .orElseThrow(() -> new GlobalException.RecursoNoEncontradoExcepcion("Curso no encontrado"));

	    model.addAttribute("curso", curso);
	    model.addAttribute("usuario", estudiante);

	   
	    if (estudiante != null) {
	        inscripcionRepositorio.findByEstudianteDniAndCursoId(estudiante.getDni(), id)
	            .ifPresent(inscripcion -> {
	                model.addAttribute("inscripcion", inscripcion);
	            });
	    }

	    return "detalleCurso";
	}

	// Borar curso
	@GetMapping("/admin/borrarCurso/{id}")
	public String borrarCurso(@PathVariable("id") Long id, RedirectAttributes ra) {
		Curso curso = cursoServicio.buscarPorId(id)
				.orElseThrow(() -> new GlobalException.RecursoNoEncontradoExcepcion("Curso no encontrado"));

		if (!curso.getListaInscripciones().isEmpty()) {
			ra.addFlashAttribute("error", "No puedes borrar un curso que tiene estudiantes inscritos.");
		} else {
			cursoServicio.eliminarPorId(id);
			ra.addFlashAttribute("mensaje", "Curso eliminado correctamente.");
		}
		return "redirect:/admin/misCursos";
	}

	
}
