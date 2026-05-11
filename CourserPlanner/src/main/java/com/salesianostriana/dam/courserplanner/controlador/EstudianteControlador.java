package com.salesianostriana.dam.courserplanner.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;

@Controller
public class EstudianteControlador {

	
	//Formulario que rellena el usuario
	@GetMapping("/estudiante")
	public String mostrarFormulario(Model model) {
		Estudiante estudiante = new Estudiante();
		model.addAttribute("estudianteFormulario",estudiante);
		return "formularioEstudiante";
	}
	
	
	@PostMapping("/anadirEstudiante")
	public String submit(@ModelAttribute("estudianteFormulario") Estudiante estudiante, Model model) {
	
		model.addAttribute("estudiante",estudiante);
		return "view";
	
}
}
