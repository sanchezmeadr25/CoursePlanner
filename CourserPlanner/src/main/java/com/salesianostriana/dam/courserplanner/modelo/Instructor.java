package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="instructor")
public class Instructor {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dni", nullable = false)
	private String dni;
	
	@Column(name="foto")
	private String fotoPerfil;
	
	@Column(name="nombre", nullable=false)
	private String nombre;

	@Column(name="especialidad",nullable=false)
	private String especialidad;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="valoracion")
	private double valoracionMedia;
	
	@Column(name="pais")
	private String pais;
	
	@Column(name="telefono", nullable=false)
	private String telefono;
	
	@Column(name="fechaNacimiento", nullable=false)
	private LocalDate fechaNacimiento;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_instructor_curso"))
	private List<Curso> curso= new ArrayList<>();
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Instructor other = (Instructor) o;
		return dni != null && dni .equals(other.getDni());
		
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
