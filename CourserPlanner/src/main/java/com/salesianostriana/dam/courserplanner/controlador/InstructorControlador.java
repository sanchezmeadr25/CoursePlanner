package com.salesianostriana.dam.courserplanner.controlador;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
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
	
	@GetMapping("/principalAdmin/instructor")
	public String mostrarEstadisticas(Model model, @AuthenticationPrincipal Usuario usuario) {
	    if (usuario == null) {
	        return "redirect:/acceso";
	    }

	    Optional<Instructor> instructor = instructorServicio.buscarPorId(usuario.getDni());
	    
	 //   model.addAttribute("totalCursos", instructorServicio.calcularTotalCursos(instructor));
	   // model.addAttribute("mediaValoracion", instructorServicio.calcularValoracionMedia(instructor));
	    model.addAttribute("instructor", instructor);
	    
	    return "admin/dashboardInstructor";
	}
		
}
