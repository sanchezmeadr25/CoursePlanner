package com.salesianostriana.dam.courserplanner.servicio;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Estudiante;
import com.salesianostriana.dam.courserplanner.repositorio.EstudianteRepositorio;
import com.salesianostriana.dam.courserplanner.servicios.base.ServicosBasesImplementados;

@Service
public class EstudianteServicio extends ServicosBasesImplementados<Estudiante, String, EstudianteRepositorio> {

}
