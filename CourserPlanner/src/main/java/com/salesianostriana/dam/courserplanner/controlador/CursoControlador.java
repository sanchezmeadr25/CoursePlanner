package com.salesianostriana.dam.courserplanner.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.modelo.Instructor;
import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio;
import com.salesianostriana.dam.courserplanner.repositorio.InstructorRepositorio;
import com.salesianostriana.dam.courserplanner.servicio.CursoServicio;
import com.salesianostriana.dam.courserplanner.servicio.InscripcionServicio;
import com.salesianostriana.dam.courserplanner.servicio.InstructorServicio;

@Controller
public class CursoControlador {

    private final InscripcionServicio inscripcionServicio;
    private final CursoRepositorio cursoRepositorio;
    private final CursoServicio cursoServicio;
    private final InstructorRepositorio instructorRepositorio;
    private final InstructorServicio instructorServicio;

    public CursoControlador(InscripcionServicio inscripcionServicio, CursoRepositorio cursoRepositorio,
                            CursoServicio cursoServicio, InstructorRepositorio instructorRepositorio,
                            InstructorServicio instructorServicio) {
        super();
        this.inscripcionServicio = inscripcionServicio;
        this.cursoRepositorio = cursoRepositorio;
        this.cursoServicio = cursoServicio;
        this.instructorRepositorio = instructorRepositorio;
        this.instructorServicio = instructorServicio;
    }

    @GetMapping("/crearCurso")
    public String mostrarFormulario(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("listaInstructores", instructorRepositorio.findAll());
        return "formularioCurso";
    }

    @PostMapping("/crearCurso/submit")
    public String guardarCurso(@ModelAttribute Curso curso) {
        List<Instructor> lista = instructorRepositorio.findAll();
        if (!lista.isEmpty()) {
            curso.setInstructor(lista.get(0));
        }
        cursoRepositorio.save(curso);
        return "redirect:/admin/misCursos";
    }

    @GetMapping("admin/misCursos")
    public String listarMisCursos(Model model, @AuthenticationPrincipal Usuario usuario) {
        
        model.addAttribute("listaCurso", cursoServicio.buscarCursoPorInstructor(usuario.getDni()));
        return "admin/listaCursos";
    }

    @GetMapping("/catalogo")
    public String listarTodosLosCursos(Model model) {
        model.addAttribute("cursos", cursoServicio.buscarTodos());
        return "listaCursosEstudiante";
    }

    @GetMapping("/misCursosInscritos")
    public String listarMisCursosInscritos(Model model, @AuthenticationPrincipal Usuario usuario) {
        
        List<Curso> misCursos = inscripcionServicio.buscarCursosPorEstudiante(usuario.getDni());
        
        if (misCursos != null) {
            misCursos.removeIf(Objects::isNull);
        }
        
        model.addAttribute("misCursos", (misCursos != null) ? misCursos : new ArrayList<Curso>());
        
        return "misCursosInscritos";
    }
}
