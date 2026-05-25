package com.salesianostriana.dam.courserplanner.controlador;

import com.salesianostriana.dam.courserplanner.modelo.Usuario; // Asegúrate de importar tu clase Usuario
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio; // Asegúrate de importar tu repo
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio; // Asegúrate de importar tu repo
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class ControladorPrincipal {

    @Autowired 
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @GetMapping("/principalAdmin") 
    public String principalAdmin(Model model, Principal principal) {
    	Usuario usuario = usuarioRepositorio.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

       
        model.addAttribute("usuario", usuario);
        model.addAttribute("numCursos", cursoRepositorio.countByInstructor_Dni(usuario.getDni()));
        return "principalAdmin"; 
    }
}