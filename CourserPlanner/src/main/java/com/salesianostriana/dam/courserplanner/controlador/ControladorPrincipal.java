package com.salesianostriana.dam.courserplanner.controlador;

import com.salesianostriana.dam.courserplanner.modelo.Usuario; 
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio; 
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio; 
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class ControladorPrincipal {

    private final UsuarioRepositorio usuarioRepositorio;

    private final CursoRepositorio cursoRepositorio;

    @GetMapping("/principalAdmin") 
    public String principalAdmin(Model model, @AuthenticationPrincipal Usuario usuario) {
        // Ya no necesitas buscarlo en el repositorio, 'usuario' ya está aquí
        model.addAttribute("usuario", usuario);
        model.addAttribute("numCursos", cursoRepositorio.countByInstructor_Dni(usuario.getDni()));
        return "principalAdmin"; 
    }

    @GetMapping("/principalUser") 
    public String principalUser(Model model, @AuthenticationPrincipal Usuario usuario) {
        // Si el usuario es de tipo Estudiante, ten cuidado con las consultas de instructor
        model.addAttribute("usuario", usuario);
        
        // OJO: Si este método es para Estudiantes, countByInstructor_Dni puede fallar o devolver 0.
        // Asegúrate de que la lógica sea coherente con el tipo de usuario.
        model.addAttribute("numCursos", cursoRepositorio.countByInstructor_Dni(usuario.getDni()));
        
        return "principalUser"; 
    }
}