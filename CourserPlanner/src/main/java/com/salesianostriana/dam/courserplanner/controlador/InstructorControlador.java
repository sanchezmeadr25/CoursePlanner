package com.salesianostriana.dam.courserplanner.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.servicio.InstructorServicio;

@Controller
public class InstructorControlador {

	private InstructorServicio  instructorServicio;
	
	
	//Formulacrio crear Instructor
	@GetMapping("/crearInstructor")
	public String mostrarFormulario(Model model) {
		Instructor instructor  = new Instructor();
			model.addAttribute("instructor",instructor);
			return "formularioInstructor";
	
	}
	
	@PostMapping("/crearInstructor")
	public String submit(@ModelAttribute("instructorFormulario") Instructor instructor) {
		instructorServicio.guardar(instructor);;
		return "redirect:/";
	}
	
	@GetMapping("/dashboard/instructor")
	public String mostrarDashboard(Model model,@AuthenticationPrincipal Usuario usuario) {
	    Instructor instructor = instructorServicio.buscarPorEmail(principal.getName());
	    
	    // Llamamos al servicio para cada dato y lo añadimos al modelo
	    model.addAttribute("totalCursos", instructorServicio.calcularTotalCursos(instructor));
	    model.addAttribute("mediaValoracion", instructorServicio.calcularValoracionMedia(instructor));
	    model.addAttribute("instructor", instructor);
	    
	    return "admin/dashboardInstructor";
	}
		
}
