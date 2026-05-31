package com.salesianostriana.dam.courserplanner.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.exepciones.GlobalException;
import com.salesianostriana.dam.courserplanner.modelo.Curso;
import com.salesianostriana.dam.courserplanner.repositorio.CursoRepositorio;
import com.salesianostriana.dam.courserplanner.serviciobase.ServicosBasesImplementados;


@Service
public class CursoServicio extends ServicosBasesImplementados<Curso, Long, CursoRepositorio> {

    private final CursoRepositorio cursoRepositorio;

    public CursoServicio(CursoRepositorio cursoRepositorio) {
        super(cursoRepositorio);
        this.cursoRepositorio = cursoRepositorio;
    }

    
    //Guardamo curso
    @Override
    public Curso guardar(Curso curso) {
        curso.calcularDuracion(); 
        return cursoRepositorio.save(curso);
    }
    
    
    //Buscamos toso los cusos
    public List<Curso> buscarTodos() {
        return cursoRepositorio.findAll();
    }
    
    //Buscamos los cursos que tiene un Instructpr
    public List<Curso> buscarCursoPorInstructor(String dni) {
        return cursoRepositorio.findByInstructor_Dni(dni);
    }

    //Buscamos por Id del curso
    public Optional<Curso> buscarPorId(Long id) {
        return super.buscarPorId(id);
    }
    
   //Vemos cuantas plazas quedan disponibl3
    public void validarPlazas(Curso curso) {
        if (curso.getListaInscripciones().size() >= curso.getPlazasMaximas()) {
            
            throw new GlobalException.CursoCompletoExcepcion("El curso con ID " + curso.getId() + " no tiene plazas disponibles.");
        }
    }
}