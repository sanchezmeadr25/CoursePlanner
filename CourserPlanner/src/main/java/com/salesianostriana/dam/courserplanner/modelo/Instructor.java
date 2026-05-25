package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Instructor extends Usuario{

	

	@Column(name = "especialidad", nullable = false)
	private String especialidad;


	@Column(name = "valoracion")
	private double valoracionMedia;
	
	

	

	@OneToMany(mappedBy = "instructor", 
			orphanRemoval = true)
	@Builder.Default
	@ToString.Exclude
	private List<Curso> curso = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Instructor other = (Instructor) o;
		return dni != null && dni.equals(other.getDni());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public Instructor() {
	    super();
	}
}