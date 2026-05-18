package com.salesianostriana.dam.courserplanner.controlador;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.servicio.EstudianteServicio;

@Controller
public class EstudianteControlador {

	private EstudianteServicio estudianteServicio;
	
	//Formulario que crea el estudiante
	@GetMapping("/anadirestudiante")
	public String mostrarFormulario(Model model) {
		Estudiante estudiante = new Estudiante();
		model.addAttribute("estudianteFormulario",estudiante);
		return "formularioEstudiante";
	}
	
	
	@PostMapping("/crearEstudiante")
	public String submit(@ModelAttribute("estudianteFormulario") Estudiante estudiante) {
		estudianteServicio.guardar(estudiante);
		return "redirect:/";
	}
	
	
	/*
	//Formulario de editar el estudiante
	@GetMapping("/editarEstudiante/{dni}")
	public String formularioEditar(@PathVariable("dni") String dni, Model model ) {
		Optional<Estudiante> estudianteEditar= estudianteServicio.buscarPorId(dni);
		
		if (estudianteEditar.isPresent()) {
		model.addAttribute("estudianteFormulario",estudianteEditar.get());
		return "formulario";
			}else {
				return "redirect:/";
			}
	}*/
}

