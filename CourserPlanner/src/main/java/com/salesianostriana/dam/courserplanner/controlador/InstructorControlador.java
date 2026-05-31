package com.salesianostriana.dam.courserplanner.controlador;

import java.util.Optional;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Formulario para editar 
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
    public String procesarFormularioEdicion(@ModelAttribute("instructor") Instructor i) {
        instructorServicio.guardar(i);
        return "redirect:/principalAdmin";
    }

    
    //Principa instructor
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