package com.salesianostriana.dam.courserplanner.controlador;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.EstudianteRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio;
import com.salesianostriana.dam.courserplanner.servicio.EstudianteServicio;

import jakarta.validation.Valid;

@Controller
public class EstudianteControlador {
	
	private final EstudianteRepositorio estudianteRepositorio;
	private final EstudianteServicio estudianteServicio;
	private final UsuarioRepositorio usuarioRepositorio;
	
	public EstudianteControlador(EstudianteServicio estudianteServicio, UsuarioRepositorio usuarioRepositorio,EstudianteRepositorio estudianteRepositorio) {
		super();
		this.estudianteServicio = estudianteServicio;
		this.usuarioRepositorio= usuarioRepositorio;
		this.estudianteRepositorio= estudianteRepositorio;
	}

	// Formulario que crea el estudiante
	@GetMapping("/anadirestudiante")
	public String mostrarFormulario(Model model, Principal principal) {
	    String username = principal.getName();
	    
	    
	    Usuario usuario = usuarioRepositorio.findByUsername(username).orElse(null);
	    
	    model.addAttribute("usuario", usuario);
	    model.addAttribute("estudianteFormulario", new Estudiante());
	    
	    return "formularioEstudiante";
	}

	@PostMapping("/anadirestudiante/submit")
	public String submit(@ModelAttribute("estudianteFormulario") Estudiante anadirestudiante) {
	    estudianteServicio.guardar(anadirestudiante);
	
	    return "redirect:/admin/listaEstudiantes";
	}
	
	
	// Formulario de editar el estudiante
	@GetMapping("/editarEstudiante/{dni}")
	public String mostrarFormularioEdicion(@PathVariable("dni")String dni, Model model) {
	
		Optional<Estudiante> estudiante = estudianteServicio.findById(dni);
		
		if (estudiante.isPresent()) {
			model.addAttribute("estudianteFormulario", estudiante.get());
			return "formularioEstudiante";
		} else {
			return "redirect:/admin/listaEstudiantes";
		}	

	}
	
	
	@PostMapping("/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("estudianteFormulariol") Estudiante e) {
		estudianteServicio.editar(e);	
		
		return "redirect:/admin/listaEstudiantes";
		
	}

	//Lista de estudiantes 
	@GetMapping("/admin/listaEstudiantes")
	public String listarEstudiantes(Model model, Principal principal) {
		
	    Usuario usuario = usuarioRepositorio.findByUsername(principal.getName())
	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	    model.addAttribute("usuario", usuario);

	  
	    List<Estudiante> estudiantes = estudianteRepositorio.findAll();
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
