package com.salesianostriana.dam.courserplanner.modelo;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "curso")
@ToString(exclude = {"instructor", "listaInscripciones"})
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	
	@NotBlank(message = "El título no puede estar vacío")
	@Size(max = 300, message = "El título no puede exceder los 300 caracteres")
	@Column(name = "titulo", nullable = false, length = 300)
	private String titulo;

	@Column(name = "descripcion", columnDefinition = "Text")
	private String descripcion;

	@NotBlank(message = "La categoría es obligatoria")
	@Column(name = "categoria", nullable = false)
	private String categoria;

	
	@Column(name = "foto")
	private String fotoCurso;

	@DecimalMin(value = "0.0", message = "El precio no puede ser negativo")
	@Column(name = "precio", nullable = false)
	private double precio;
	
	@Min(value = 1, message = "Debe haber al menos 1 plaza")
	@Column(name = "plazas_maximas", nullable = false)
	private int plazasMaximas;
	
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "100.0", message = "El descuento no puede superar el 100%")
	@Column(name = "descuento")
	private double descuento;
	
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "5.0")
	@Column(name = "valoracion")
	private double valoracion;
	
	@Column(name = "duracion")
	private Duration duracionHoras;
	
    @Transient 
    private int horasForm;
    @Transient 
    private int minutosForm;

    
    public void calcularDuracion() {
        this.duracionHoras = Duration.ofHours(horasForm).plusMinutes(minutosForm);
    }
	
    public String getDuracionFormateada() {
        long h = duracionHoras.toHours();
        long m = duracionHoras.toMinutesPart(); 
        return h + "h " + m + "m";
    }
    
    
    
    public double getPrecioFinal() {
        return this.precio - (this.precio * this.descuento / 100);
    }
    
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_instructor_curso"))
	private Instructor instructor;

	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<Inscripcion> listaInscripciones = new ArrayList<>();

	
	// Métodos helper porque es una asociación Biderrrecional
	public void addInscripcion(Inscripcion inscripcion) {
	    this.listaInscripciones.add(inscripcion);
	    inscripcion.setCurso(this);
	}

	public void removeInscripcion(Inscripcion inscripcion) {
	    this.listaInscripciones.remove(inscripcion);
	    inscripcion.setCurso(null);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Curso other = (Curso) o;
		return id != null && id.equals(other.getId());

	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
