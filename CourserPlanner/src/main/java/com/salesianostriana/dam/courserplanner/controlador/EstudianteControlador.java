package com.salesianostriana.dam.courserplanner.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.servicio.EstudianteServicio;

import jakarta.validation.Valid;

@Controller
public class EstudianteControlador {
	
	private final EstudianteServicio estudianteServicio;
	
	public EstudianteControlador(EstudianteServicio estudianteServicio) {
		super();
		this.estudianteServicio = estudianteServicio;
	}

	// Formulario que crea el estudiante
	@GetMapping("/anadirestudiante")
	public String mostrarFormulario(Model model, @AuthenticationPrincipal Usuario usuario) {
		
	    model.addAttribute("usuario", usuario);
	    model.addAttribute("estudianteFormulario", new Estudiante());
	    return "formularioEstudiante";
	}

	@PostMapping("/anadirestudiante/submit")
	public String submit(@Valid @ModelAttribute("estudianteFormulario") Estudiante anadirestudiante, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formularioEstudiante";
		}
	    estudianteServicio.guardar(anadirestudiante);
	    return "redirect:/admin/listaEstudiantes";
	}
	
	// Formulario de editar el estudiante
	@GetMapping("/admin/editarEstudiante/{dni}")
	public String mostrarFormularioEdicion(@PathVariable("dni") String dni, 
                                       Model model, 
                                       @AuthenticationPrincipal Usuario usuarioLogueado) {
	   
	    Optional<Estudiante> estudiante = estudianteServicio.buscarPorId(dni);
	   
	    if (estudiante.isPresent()) {
	        model.addAttribute("usuario", usuarioLogueado);
	        model.addAttribute("estudianteFormulario", estudiante.get());
	        return "formularioEstudiante";
	    } else {
	        return "redirect:/admin/listaEstudiantes";
	    }	
	}
	
	@PostMapping("admin/editarFormularioEdicion/submit")
	public String procesarFormularioEdicion(@Valid @ModelAttribute("estudianteFormulario") Estudiante e, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formularioEstudiante";
		}
		estudianteServicio.guardar(e);	
		return "redirect:/admin/listaEstudiantes";
	}

	// Lista de estudiantes
	@GetMapping("/admin/listaEstudiantes")
	public String listarEstudiantes(Model model, @AuthenticationPrincipal Usuario usuario) {
	    
	    model.addAttribute("usuario", usuario);
	    
	    List<Estudiante> estudiantes = estudianteServicio.obtenerTodosLosEstudiantes();
	    model.addAttribute("estudiantes", estudiantes); 
	    
	    return "admin/listaEstudiantes";
	}
	
	@GetMapping("/admin/estudiante/borrar/{dni}")
	public String borrar(@PathVariable("dni") String dni) {
		Optional<Estudiante> aBorrar = estudianteServicio.buscarPorId(dni);		
		if (aBorrar.isPresent()) {
			estudianteServicio.eliminarPorId(dni);
		} 

		return "redirect:/admin/listaEstudiantes";		
	}
}