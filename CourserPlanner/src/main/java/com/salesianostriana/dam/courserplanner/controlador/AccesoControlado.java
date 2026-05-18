package com.salesianostriana.dam.courserplanner.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccesoControlado {

	@GetMapping("/acceso")
	public String mostrarFormularioAcceso() {
		return "acceso";
	}
}
