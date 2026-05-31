package com.salesianostriana.dam.courserplanner.controlador;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.EstadoInscripcion;
import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Inscripcion;
import com.salesianostriana.dam.courserplanner.modelo.InscripcionPK;
import com.salesianostriana.dam.courserplanner.repositorio.EstudianteRepositorio;
import com.salesianostriana.dam.courserplanner.servicio.CursoServicio;
import com.salesianostriana.dam.courserplanner.servicio.InscripcionServicio;

@Controller
@RequestMapping("/inscripcion")
public class InscripcionControlador {

	private final InscripcionServicio inscripcionServicio;
    private final EstudianteRepositorio estudianteRepositorio;
    private final CursoServicio cursoServicio; 

  
    public InscripcionControlador(InscripcionServicio inscripcionServicio, EstudianteRepositorio estudianteRepositorio,
			CursoServicio cursoServicio) {
		super();
		this.inscripcionServicio = inscripcionServicio;
		this.estudianteRepositorio = estudianteRepositorio;
		this.cursoServicio = cursoServicio;
	}

	@GetMapping("/nueva")
    public String mostrarFormulario(@RequestParam(required = false) Long cursoId, 
                                    Model model, 
                                    @AuthenticationPrincipal Estudiante estudianteLogueado) {
        
        Inscripcion inscripcion = new Inscripcion();
        InscripcionPK pk = new InscripcionPK();
        
        if (cursoId != null) {
            pk.setCursoId(cursoId);
          
            Curso curso = cursoServicio.findById(cursoId).orElse(null);
            model.addAttribute("curso", curso);
        }
        
        inscripcion.setInscripcionPK(pk); 
        
        model.addAttribute("usuario", estudianteLogueado);
        model.addAttribute("inscripcion", inscripcion); 
        
        return "formularioInscripcion";
    }
    
    @PostMapping("/nuevainscripcion/submit") 
    public String procesarInscripcion(@ModelAttribute("inscripcion") Inscripcion inscripcion, 
                                      @AuthenticationPrincipal Estudiante estudianteLogueado) {
    
      
        inscripcion.getInscripcionPK().setEstudianteDni(estudianteLogueado.getDni());
       
        inscripcion.setEstado(EstadoInscripcion.PENDIENTE);
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        
        inscripcionServicio.registrarInscripcion(inscripcion);
        
        return "redirect:/principalUser";
    }
}