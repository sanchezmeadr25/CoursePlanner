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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
public class Instructor extends Usuario{

	
	@NotEmpty(message = "La especialidad no puede estar vacía")
	@Column(name = "especialidad", nullable = false)
	private String especialidad;

	@Min(0) 
    @Max(5)
	@Column(name = "valoracion")
	private double valoracionMedia;
	
	

	

	@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
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