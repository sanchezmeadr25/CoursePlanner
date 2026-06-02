package com.salesianostriana.dam.courserplanner.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.repositorio.EstudianteRepositorio;
import com.salesianostriana.dam.courserplanner.serviciobase.ServicosBasesImplementados;
import com.salesianostriana.dam.courserplanner.exepciones.GlobalException;

@Service
public class EstudianteServicio extends ServicosBasesImplementados<Estudiante, String, EstudianteRepositorio> {

    private final EstudianteRepositorio estudianteRepositorio;

    public EstudianteServicio(EstudianteRepositorio estudianteRepositorio) {
        super(estudianteRepositorio);
        this.estudianteRepositorio = estudianteRepositorio;
    }

    //Obtenemos todos los estudiantes
    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepositorio.findAll();
    }

    //Guardamos al estudiante
    public Estudiante guardar(Estudiante e) {
        return estudianteRepositorio.save(e);
    }
    
    
    public Optional<Estudiante> buscarPorId(String dni) {
		return estudianteRepositorio.findById(dni);
	}

    //buscvamos al estudiante pior el dni y decimos si no lo encontramos
    public Estudiante buscarPirId(String dni) {
        return estudianteRepositorio.findById(dni)
                .orElseThrow(() -> new GlobalException.RecursoNoEncontradoExcepcion("No se encontró el estudiante con DNI: " + dni));
    }

    //eliminamos al estudiante por el dni
    public void eliminarPorId(String dni) {
        estudianteRepositorio.deleteById(dni);
    }
}