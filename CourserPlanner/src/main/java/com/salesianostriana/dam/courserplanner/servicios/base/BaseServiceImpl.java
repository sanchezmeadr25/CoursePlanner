package com.salesianostriana.dam.courserplanner.servicios.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public class BaseServiceImpl<T, ID, R extends JpaRepository<T,ID>>implements BaseService<T,ID>{

	@Override
	public List<T> findAll() {
		
		return null;
	}

	@Override
	public Optional<T> findById(ID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public T save(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T edit(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(ID id) {
		// TODO Auto-generated method stub
		
	}

}
