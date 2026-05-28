package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inscripcion")
@ToString(exclude = {"estudiante", "curso"})
public class Inscripcion {

    @EmbeddedId
    private InscripcionPK inscripcionPK = new InscripcionPK();

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDateTime fechaInscripcion;

    @Column(name = "progreso")
    private double progreso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoInscripcion estado;

    private double valoracion;

   
    @ManyToOne
    @MapsId("estudianteDni") // Coincide con el campo en InscripcionPK
    @JoinColumn(name = "estudiante_dni")
    private Estudiante estudiante;


    @ManyToOne
    @MapsId("cursoId")       // Coincide con el campo en InscripcionPK
    @JoinColumn(name = "curso_id")
    private Curso curso;


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Inscripcion other = (Inscripcion) o;
		return inscripcionPK != null && inscripcionPK.equals(other.getInscripcionPK());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}