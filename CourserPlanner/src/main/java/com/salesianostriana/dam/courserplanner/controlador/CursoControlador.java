package com.salesianostriana.dam.courserplanner.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.repositorio.InstructorRepositorio;
import com.salesianostriana.dam.courserplanner.servicio.CursoServicio;

@Controller
public class CursoControlador {

	private final CursoServicio cursoServicio;
	private final InstructorRepositorio instructorRepositorio;
	
	
	public CursoControlador(CursoServicio cursoServicio, InstructorRepositorio instructorRepositorio) {
		super();
		this.cursoServicio = cursoServicio;
		this.instructorRepositorio = instructorRepositorio;
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
	
	
	
	
	
	
	
	
	
	
	
	
}
