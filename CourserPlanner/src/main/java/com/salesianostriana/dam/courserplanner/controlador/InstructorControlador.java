package com.salesianostriana.dam.courserplanner.controlador;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.servicio.InstructorServicio;

@Controller
public class InstructorControlador {

    private final InstructorServicio instructorServicio;

  
    public InstructorControlador(InstructorServicio instructorServicio) {
        this.instructorServicio = instructorServicio;
    }

    // Formulario crear Instructor
    @GetMapping("/crearInstructor")
    public String mostrarFormulario(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "formularioInstructor";
    }

    @PostMapping("/crearInstructor")
    public String submit(@ModelAttribute("instructor") Instructor instructor) {
        instructorServicio.guardar(instructor);
        return "redirect:/";
    }
    

    @GetMapping("/dashboard/instructor")
    public String mostrarDashboard(Model model, @AuthenticationPrincipal Usuario usuario) {
  
        if (usuario == null) {
            return "redirect:/acceso";
        }

      
        Optional<Instructor> instructor = instructorServicio.buscarPorId(usuario.getDni());
        
      
        instructor.ifPresent(i -> model.addAttribute("instructor", i));
        
        return "admin/dashboardInstructor";
    }
}