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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@Table(name="estudiante")
public abstract class Estudiante {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dni", nullable = false)
	private String dni;
	
	@Column(name="foto")
	private String fotoPerfil;
	
	@Column(name="nombre", nullable=false)
	private String nombre;
	
	@Column(name="apellido", nullable=false)
	private String apellidos;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="experiencia")
	private String nivelExperiencia;
	
	@Column(name="telefono", nullable=false)
	private String telefono;
	
	@Column(name="fechaNacimiento", nullable=false)
	private LocalDate fechaNacimiento;
	
	@Column(name="pais")
	private String pais;
	
	@Column(name="localidad")
	private String localidad;

	@ManyToMany
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_estudiante_inscripcion"))
	private List<Inscripcion>listaInscripciones=new ArrayList <>();
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Curso other = (Curso) o;
		return dni != null && dni .equals(other.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
