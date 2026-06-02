package com.salesianostriana.dam.courserplanner.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionPK implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String estudianteDni;
    private Long cursoId;
}