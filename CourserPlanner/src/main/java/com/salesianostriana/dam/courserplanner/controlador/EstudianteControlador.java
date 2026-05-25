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
	public String mostrarFormulario(Model model) {
		Estudiante estudiante = new Estudiante();
		model.addAttribute("estudianteFormulario", estudiante);
		return "formularioEstudiante";
	}

	/*@PostMapping("/anadirestudiante/submit")
	public String submit(@ModelAttribute("estudianteFormulario") Estudiante anadirestudiante) {
		estudianteServicio.guardar(anadirestudiante);
		return "redirect:/admin/listaEstudiantes";
	}*/
	
	@PostMapping("/anadirestudiante/submit")
	public String submit(@ModelAttribute("estudianteFormulario") Estudiante estudiante) {
	    System.out.println("DEBUG: Entrando en submit con DNI: " + estudiante.getDni());
	    
	    // Forzamos el guardado directo sin validaciones
	    try {
	        estudianteServicio.guardar(estudiante);
	        System.out.println("DEBUG: Guardado exitoso");
	    } catch (Exception e) {
	        System.out.println("DEBUG: Error al guardar: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return "redirect:/";
	}

	// Formulario de editar el estudiante
	@GetMapping("/editarEstudiante/{dni}")
	public String formularioEditar(@PathVariable("dni") String dni, Model model) {
		Optional<Estudiante> estudianteEditar = estudianteServicio.buscarPorId(dni);

		if (estudianteEditar.isPresent()) {
			model.addAttribute("estudianteFormulario", estudianteEditar.get());
			return "formulario";
		} else {
			return "redirect:/";
		}

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

}
