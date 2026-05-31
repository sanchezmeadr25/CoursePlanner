package com.salesianostriana.dam.courserplanner.controlador;

import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.servicio.InstructorServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InstructorControlador {

	private final InstructorServicio instructorServicio;

	//Crear Instructor
	@GetMapping("/crearInstructor")
	public String mostrarFormulario(Model model) {
		model.addAttribute("instructor", new Instructor());
		return "formularioInstructor";
	}

	@PostMapping("/crearInstructor")
	public String submit(@Valid @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formularioInstructor";
		}
		instructorServicio.crearInstructor(instructor);
		return "redirect:/";
	}

	//Editar Instructor
	@GetMapping("/editarInstructor/{dni}")
	public String mostrarFormularioEdicion(@PathVariable("dni") String dni, Model model) {

		Optional<Instructor> instructor = instructorServicio.buscarPorId(dni);

		if (instructor.isPresent()) {
			model.addAttribute("instructor", instructor.get());
			return "formularioInstructor";
		}
		return "redirect:/principalAdmin";
	}

	
	@PostMapping("/editarInstructor/submit")
	public String procesarFormularioEdicion(@Valid @ModelAttribute("instructor") Instructor i,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formularioInstructor";
		}
		instructorServicio.editar(i);
		return "redirect:/principalAdmin";
	}

	//Principa Instructor
	@GetMapping("/dashboard/instructor")
	public String mostrarDashboard(Model model, @AuthenticationPrincipal Usuario usuario) {
		if (usuario == null) {
			return "redirect:/acceso";
		}

		Optional<Instructor> instructorOpt = instructorServicio.buscarPorId(usuario.getDni());

		if (instructorOpt.isPresent()) {
			Instructor instructor = instructorOpt.get();
			model.addAttribute("instructor", instructor);
			model.addAttribute("totalCursos", instructorServicio.calcularTotalCursos(instructor));
			model.addAttribute("valoracionMedia", instructorServicio.calcularValoracionMedia(instructor));

			return "admin/dashboardInstructor";
		}

		return "redirect:/principalAdmin";
	}
}