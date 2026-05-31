package com.salesianostriana.dam.courserplanner.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import com.salesianostriana.dam.courserplanner.repositorio.UsuarioRepositorio;

@Configuration
@EnableWebSecurity 
public class ConfiguracionSeguridad {
	
	private final CustomAuthenticationSuccessHandler successHandler;
	private final UsuarioRepositorio usuarioRepositorio;
	
	public ConfiguracionSeguridad(CustomAuthenticationSuccessHandler successHandler, UsuarioRepositorio usuarioRepositorio) {
		super();
		this.successHandler = successHandler;
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	
	@Bean
	SecurityFilterChain CadenaFiltrosSeguridad(HttpSecurity http) {
		
		//Aquí dentro lo que hacemos es decidir quien entra y quien no y a que puede entrar.
		http.authorizeHttpRequests(authz -> authz
		        .requestMatchers("/", "/principal", "/acceso", "/h2-console/**", 
		                         "/crearInstructor", "/anadirestudiante", "/eleccionCuenta", 
		                         "/css/**", "/js/**", "/img/**", "/error").permitAll()
		        .requestMatchers("/admin/**").hasRole("ADMIN")
		        .anyRequest().authenticated()
		    )
		    .requestCache(cache -> {
		        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		        requestCache.setMatchingRequestParameterName(null);
		        cache.requestCache(requestCache);
		    })
		    .formLogin(form -> form
		        .loginPage("/acceso")
		        .loginProcessingUrl("/acceso")
		        .successHandler(successHandler)
		        .permitAll()
		    )
		    
		    .logout(logout -> logout.permitAll());

		    http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
		    http.headers(headers -> headers.frameOptions(opts -> opts.disable()));

		    return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return username -> usuarioRepositorio.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
	}
		
	
}
