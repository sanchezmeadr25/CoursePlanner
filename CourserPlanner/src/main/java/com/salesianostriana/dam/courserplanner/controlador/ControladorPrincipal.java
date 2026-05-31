package com.salesianostriana.dam.courserplanner.controlador;

import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.InscripcionRepositorio;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPrincipal {

	private final CursoRepositorio cursoRepositorio;
	private InscripcionRepositorio inscripcionesRepositorio;

	public ControladorPrincipal(CursoRepositorio cursoRepositorio, InscripcionRepositorio inscripcionesRepositorio) {
		super();
		this.cursoRepositorio = cursoRepositorio;
		this.inscripcionesRepositorio = inscripcionesRepositorio;
	}

	@GetMapping("/principalAdmin")
	public String principalAdmin(Model model, @AuthenticationPrincipal Usuario usuario) {
		String dni = usuario.getDni();

		model.addAttribute("usuario", usuario);

		model.addAttribute("numCursos", cursoRepositorio.cantidadCursosInstructor(dni));
		model.addAttribute("numEstudiantes", inscripcionesRepositorio.contarEstudiantesPorInstructor(dni));
		model.addAttribute("calificacion", cursoRepositorio.mediaValoracionInstructor(dni));
		model.addAttribute("numValoraciones", inscripcionesRepositorio.countValoracionesPorInstructor(dni));

		return "principalAdmin";
	}

	@GetMapping("/principalUser")
	public String principalUser(Model model, @AuthenticationPrincipal Usuario usuario) {

		String dni = usuario.getDni();
		model.addAttribute("usuario", usuario);

		model.addAttribute("numInscritos", inscripcionesRepositorio.countCursosInscritosPorEstudiante(dni));
		model.addAttribute("numDisponibles", cursoRepositorio.count());
		model.addAttribute("numValoraciones", inscripcionesRepositorio.countValoracionesPorEstudiante(dni));

		model.addAttribute("misCursos", inscripcionesRepositorio.findAllByEstudianteDni(dni));
		model.addAttribute("cursosRecomendados", cursoRepositorio.findAll());

		return "principalUser";
	}

	@GetMapping("/eleccionCuenta")
	public String elegirCuenta() {

		return "eleccionCuenta";
	}
}