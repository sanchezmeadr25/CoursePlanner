package com.salesianostriana.dam.courserplanner.modelo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public  class Usuario implements UserDetails {

	@Id
	@Size(min = 9, max = 9, message = "El DNI debe tener 9 caracteres")
	@Column(name = "dni", length = 9, nullable = false)
	protected String dni;

	@Column(name = "foto")
	private String foto;

	@Column(name = "username", nullable = false, unique = true)
	private String username;


	@Column(name = "apellidos", nullable = false)
	private String apellidos;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "telefono", nullable = false)
	private String telefono;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "fechaNacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	@Column(name = "pais")
	private String pais;

	@Column(name = "localidad")
	private String localidad;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Rol rol;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
	}

	

}
