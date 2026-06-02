package com.salesianostriana.dam.courserplanner.serviciobase;

import java.util.List;
import java.util.Optional;

public interface ServicioBase <T,ID>{
	
	List<T> buscarTodos();

	Optional<T> buscarPorId(ID id);

	T guardar(T t);

	T editar(T t);

	void eliminar(T t);

	void eliminarPorId(ID id);
	
}
