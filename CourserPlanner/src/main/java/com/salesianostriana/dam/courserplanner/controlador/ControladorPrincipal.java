package com.salesianostriana.dam.courserplanner.controlador;

import com.salesianostriana.dam.courserplanner.modelo.Usuario; 
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio; 
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio; 
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class ControladorPrincipal {

    private final UsuarioRepositorio usuarioRepositorio;

    private final CursoRepositorio cursoRepositorio;

    public ControladorPrincipal(UsuarioRepositorio usuarioRepositorio, CursoRepositorio cursoRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
		this.cursoRepositorio = cursoRepositorio;
	}

	@GetMapping("/principalAdmin") 
    public String principalAdmin(Model model, Principal principal) {
    	Usuario usuario = usuarioRepositorio.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

       
        model.addAttribute("usuario", usuario);
        model.addAttribute("numCursos", cursoRepositorio.countByInstructor_Dni(usuario.getDni()));
        return "principalAdmin"; 
    }
    
    @GetMapping("/principalUser") 
    public String principalUser(Model model, Principal principal) {
    	Usuario usuario = usuarioRepositorio.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

       
        model.addAttribute("usuario", usuario);
        model.addAttribute("numCursos", cursoRepositorio.countByInstructor_Dni(usuario.getDni()));
        return "principalUser"; 
    }
}