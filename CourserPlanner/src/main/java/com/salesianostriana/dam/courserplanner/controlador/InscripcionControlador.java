package com.salesianostriana.dam.courserplanner.controlador;

import java.time.LocalDateTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.courserplanner.modelo.EstadoInscripcion;
import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Inscripcion;
import com.salesianostriana.dam.courserplanner.modelo.InscripcionPK;
import com.salesianostriana.dam.courserplanner.servicio.InscripcionServicio;

@Controller
@RequestMapping("/inscripcion")
public class InscripcionControlador {

	private final InscripcionServicio inscripcionServicio;
	private final EstudianteRepositorio estudianteRepositorio; 

	
	public InscripcionControlador(InscripcionServicio inscripcionServicio, EstudianteRepositorio estudianteRepositorio) {
		this.inscripcionServicio = inscripcionServicio;
		this.estudianteRepositorio = estudianteRepositorio;
	}
	@GetMapping("/nueva")
	public String mostrarFormulario(@RequestParam(required = false) Long cursoId, Model model, Principal principal) {
	    Inscripcion inscripcion = new Inscripcion();
	    InscripcionPK pk = new InscripcionPK();
	    
	    if (cursoId != null) {
	        pk.setCursoId(cursoId);
	    }
	    
	    inscripcion.setInscripcionPK(pk); 
	    estudianteRepositorio.findByUsername(principal.getName())
	            .ifPresent(usuario -> model.addAttribute("usuario", usuario));
	    model.addAttribute("inscripcion", inscripcion); 
	    return "formularioInscripcion";
	}
	
	
	@PostMapping("/nuevainscripcion/submit") 
	public String procesarInscripcion(@ModelAttribute("inscripcion") Inscripcion inscripcion, 
	                                  Principal principal) {
	
	    String username = principal.getName();
	  
	    Estudiante estudiante = estudianteRepositorio.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado para el usuario: " + username));
	    
	
	    inscripcion.getInscripcionPK().setEstudianteDni(estudiante.getDni());
	   
	    inscripcion.setEstado(EstadoInscripcion.PENDIENTE);
	    
	    inscripcion.setFechaInscripcion(LocalDateTime.now());
	    
	    inscripcionServicio.registrarInscripcion(inscripcion);
	    
	    return "redirect:/principalUser";
	}
}
