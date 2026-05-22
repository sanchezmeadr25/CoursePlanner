package com.salesianostriana.dam.courserplanner.servicio;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.courserplanner.modelo.Usuario;
import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio;
import com.salesianostriana.dam.courserplanner.serviciobase.ServicosBasesImplementados;

@Service
public class UsuarioServicio extends ServicosBasesImplementados<Usuario,String,UsuarioRepositorio> {

	public UsuarioServicio(UsuarioRepositorio repositorio) {
		super(repositorio);
		// TODO Auto-generated constructor stub
	}

	private UsuarioRepositorio usuarioRepositorio;

	
	
}
