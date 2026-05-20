package com.salesianostriana.dam.courserplanner.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.servicio.EstudianteServicio;

@Controller
public class EstudianteControlador {

	private EstudianteServicio estudianteServicio;
	
	//Formulario que crea el estudiante
	@GetMapping("/anadirEstudiante")
	public String mostrarFormulario(Model model) {
		model.addAttribute("estudiante",new Estudiante());
		return "formularioEstudiante";
	}
	
	
	@PostMapping("/anadirEstudiante/submit")
	public String submit(@ModelAttribute("estudiante") Estudiante estudiante) {
		estudianteServicio.guardar(estudiante);
		return "redirect:/";
	}
	
	//Lista Estudiantes
	@GetMapping("/listaEstudiantes")
	public String listarEstudiantes(Model model) {
		model.addAttribute("estudiantes",estudianteServicio.buscarTodos());
		return "admin/listaEstudiantes";
	}
	
	
	
	
}

