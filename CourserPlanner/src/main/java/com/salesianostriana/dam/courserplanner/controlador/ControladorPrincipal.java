package com.salesianostriana.dam.courserplanner.controlador;

import com.salesianostriana.dam.courserplanner.modelo.Usuario; 
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio; 
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class ControladorPrincipal {

    private final CursoRepositorio cursoRepositorio;

    public ControladorPrincipal(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    @GetMapping("/principalAdmin") 
    public String principalAdmin(Model model, @AuthenticationPrincipal Usuario usuario) {
      
        model.addAttribute("usuario", usuario);
        model.addAttribute("numCursos", cursoRepositorio.countByInstructor_Dni(usuario.getDni()));
        return "principalAdmin"; 
    }

    @GetMapping("/principalUser") 
    public String principalUser(Model model, @AuthenticationPrincipal Usuario usuario) {
       
        model.addAttribute("usuario", usuario);
        
       
        model.addAttribute("numCursos", cursoRepositorio.countByInstructor_Dni(usuario.getDni()));
        
        return "principalUser"; 
    }
}
