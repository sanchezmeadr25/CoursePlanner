package com.salesianostriana.dam.courserplanner.controlador;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.courserplanner.exepciones.GlobalException;
import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.EstadoInscripcion;
import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.modelo.Inscripcion;
import com.salesianostriana.dam.courserplanner.modelo.InscripcionPK;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.repositorio.InscripcionRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.InstructorRepositorio;
import com.salesianostriana.dam.courserplanner.servicio.CursoServicio;
import com.salesianostriana.dam.courserplanner.servicio.InscripcionServicio;


@Controller
@RequestMapping("/inscripcion")
public class InscripcionControlador {

	private final InscripcionServicio inscripcionServicio;
    private final CursoServicio cursoServicio; 
    private final InscripcionRepositorio inscripcionRepositorio;
    private final InstructorRepositorio instructorRepositorio;

	public InscripcionControlador(InscripcionServicio inscripcionServicio, CursoServicio cursoServicio,
			InscripcionRepositorio inscripcionRepositorio, InstructorRepositorio instructorRepositorio) {
		super();
		this.inscripcionServicio = inscripcionServicio;
		this.cursoServicio = cursoServicio;
		this.inscripcionRepositorio = inscripcionRepositorio;
		this.instructorRepositorio = instructorRepositorio;
	}

    // Detalle del curso para el estudiante
	@GetMapping("/cursoDetalle/{id}")
	public String mostrarDetalleCurso(@PathVariable("id") Long id, Model model,
	                                  @AuthenticationPrincipal Estudiante estudiante) {

        Curso curso = cursoServicio.buscarPorId(id)
                .orElseThrow(() -> new GlobalException.RecursoNoEncontradoExcepcion("Curso no encontrado"));

        model.addAttribute("curso", curso);
        model.addAttribute("usuario", estudiante);

        if (estudiante != null) {
            inscripcionRepositorio.findByEstudianteDniAndCursoId(estudiante.getDni(), id)
                .ifPresent(inscripcion -> model.addAttribute("inscripcion", inscripcion));
        }

        return "detalleCurso";
    }

    // Valoración del estudiante
    @PostMapping("/estudiante/valorar")
    public String valorarComoEstudiante(@RequestParam("cursoId") Long cursoId,
                                        @RequestParam("valoracion") Double valoracion,
                                        @AuthenticationPrincipal Estudiante estudiante) {
        
        inscripcionRepositorio.findByEstudianteDniAndCursoId(estudiante.getDni(), cursoId)
            .ifPresent(inscripcion -> {
                inscripcion.setValoracion(valoracion);
                inscripcionRepositorio.save(inscripcion);
                
                String instructorDni = inscripcion.getCurso().getInstructor().getDni();
                Double nuevaMedia = inscripcionRepositorio.calcularMediaValoracionInstructor(instructorDni);
                
                Instructor instructor = inscripcion.getCurso().getInstructor();
                instructor.setValoracionMedia(nuevaMedia != null ? nuevaMedia : 0.0);
                instructorRepositorio.save(instructor);
            });
        
        return "redirect:/inscripcion/cursoDetalle/" + cursoId;
    }

	//Crear Inscripcion
	@GetMapping("/nueva")
    public String mostrarFormulario(@RequestParam(required = false) Long cursoId, 
                                    Model model, 
                                    @AuthenticationPrincipal Estudiante estudianteLogueado) {
        
        Inscripcion inscripcion = new Inscripcion();
        InscripcionPK pk = new InscripcionPK();
        
        if (cursoId != null) {
            pk.setCursoId(cursoId);
          
            Curso curso = cursoServicio.buscarPorId(cursoId).orElse(null);
            model.addAttribute("curso", curso);
        }
        
        inscripcion.setInscripcionPK(pk); 
        
        model.addAttribute("usuario", estudianteLogueado);
        model.addAttribute("inscripcion", inscripcion); 
        
        return "formularioInscripcion";
    }
    
    @PostMapping("/nuevainscripcion/submit") 
    public String procesarInscripcion(@ModelAttribute("inscripcion") Inscripcion inscripcion, 
                                      @AuthenticationPrincipal Estudiante estudianteLogueado,
                                      RedirectAttributes ra) {
    
      
        inscripcion.getInscripcionPK().setEstudianteDni(estudianteLogueado.getDni());
        Long cursoId = inscripcion.getInscripcionPK().getCursoId();

        if (inscripcionRepositorio.findByEstudianteDniAndCursoId(estudianteLogueado.getDni(), cursoId).isPresent()) {
            ra.addFlashAttribute("error", "Ya estas inscrito en este curso.");
            return "redirect:/misCursosInscritos";
        }
       
        inscripcion.setEstado(EstadoInscripcion.PENDIENTE);
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        
        inscripcionServicio.registrarInscripcion(inscripcion);
        ra.addFlashAttribute("mensaje", "Inscripcion realizada correctamente.");
        
        return "redirect:/principalUser";
    }
    
 // Inscripciones
 	@GetMapping("/admin/inscripciones/{id}")
 	public String listarInscripciones(@PathVariable("id") Long cursoId, Model model) {
 	    
 	    model.addAttribute("inscripciones", inscripcionRepositorio.findByCursoId(cursoId));
 	    model.addAttribute("cursoId", cursoId);
 	    
 	    return "admin/listaInscripciones";
 	}
 	
 	
 	@PostMapping("/admin/inscripciones/editar")
 	public String editarInscripcion(@RequestParam("estudianteDni") String dni,
 	                                @RequestParam("cursoId") Long cursoId,
 	                                @RequestParam("estado") EstadoInscripcion estado,
 	                                @RequestParam("progreso") int progreso) {
 	  
 	    
 	    inscripcionRepositorio.findByEstudianteDniAndCursoId(dni, cursoId)
 	        .ifPresent(inscripcion -> {
 	          
 	            inscripcion.setEstado(estado);
 	            inscripcion.setProgreso(progreso);
 	            
 	         
 	            inscripcionRepositorio.save(inscripcion);
 	        });
 	    
 	    return "redirect:/inscripcion/admin/inscripciones/" + cursoId;
 	}
    
    //Gestionar las inscripciones
    
    @PostMapping("/admin/inscripciones/valorar")
    public String valorarProfesor(@RequestParam("estudianteDni") String dni,
                                  @RequestParam("cursoId") Long cursoId,
                                  @RequestParam("valoracion") Double valoracion) {
        
     
        inscripcionRepositorio.findByEstudianteDniAndCursoId(dni, cursoId)
            .ifPresent(inscripcion -> {
                
                inscripcion.setValoracion(valoracion);
                inscripcionRepositorio.save(inscripcion);
                
            
                String instructorDni = inscripcion.getCurso().getInstructor().getDni();
                Double nuevaMedia = inscripcionRepositorio.calcularMediaValoracionInstructor(instructorDni);
                
                Instructor instructor = inscripcion.getCurso().getInstructor();
                instructor.setValoracionMedia(nuevaMedia);
                instructorRepositorio.save(instructor);
            });
        
    
        return "redirect:/inscripcion/admin/inscripciones/" + cursoId;
    }
}
