package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDateTime;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "inscripcion")
public class Inscripcion {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name="fechaInscripcion", nullable = false)
	private LocalDateTime fechaInscripcion;
	
	@Column(name="progreso")
	private double progreso;
	
	@Column(name="estado", nullable = false)
	private EstadoInscripcion estado;
	
	@Column(name="valoracion")
	private double valoracion;
	
	@ManyToMany
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_inscripcion_estudiante"))
	private List<Estudiante> listaEstudiantes= new ArrayList<>();
	
	
	public enum EstadoInscripcion{
		PENDIENTE, EN_CURSO, COMPLETADO, CANCELADO
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Curso other = (Curso) o;
		return id != null && id.equals(other.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
