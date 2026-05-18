package com.salesianostriana.dam.courserplanner.serviciobase;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import lombok.RequiredArgsConstructor;


public abstract class ServicosBasesImplementados <T, ID, R extends JpaRepository<T,ID>>implements ServicioBase<T,ID>{
	
	
	private final R repositorio;
	
	public ServicosBasesImplementados(R repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public List<T> buscarTodos() {
		return repositorio.findAll();
	}

	@Override
	public Optional<T> buscarPorId(ID id) {
		return repositorio.findById(id);
	}

	@Override
	public T guardar(T t) {
		return repositorio.save(t);
	}

	@Override
	public T editar(T t) {
		return repositorio.save(t);
	}

	@Override
	public void eliminar(T t) {
		repositorio.delete(t);
	}

	@Override
	public void eliminarPorId(ID id) {
		repositorio.deleteById(id);
	}

}
