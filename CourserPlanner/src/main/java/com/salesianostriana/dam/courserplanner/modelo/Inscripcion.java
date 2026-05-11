package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
 @Entity
public class Inscripcion {
	
	@Id @GeneratedValue
	private Long id;
	private LocalDateTime fechaInscripcion;
	private double progreso;
	private EstadoInscripcion estado;
	private double valoracion;
	
	
	public enum EstadoInscripcion{
		PENDIENTE, EN_CURSO, COMPLETADO, CANCELADO
	}

}
