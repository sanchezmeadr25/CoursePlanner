package com.salesianostriana.dam.courserplanner.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity //esto sirve para activar la seguridad y que se apliquen las que indico
public class ConfiguracionSeguridad {
	
	private final CustomAuthenticationSuccessHandler successHandler;
	
	public ConfiguracionSeguridad(CustomAuthenticationSuccessHandler successHandler) {
		super();
		this.successHandler = successHandler;
	}
	
	
	@Bean
	SecurityFilterChain CadenaFiltrosSeguridad(HttpSecurity http) {
		
		//Aquí dentro lo que hacemos es decidir quien entra y quien no y a que puede entrar.
		http.authorizeHttpRequests(authz -> authz
		        .requestMatchers("/", "/principal", "/acceso", "/h2-console/**", 
		                         "/crearInstructor", "/anadirestudiante", "/registro/seleccion", 
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
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		UserDetails user = User.builder().username("user").password("{noop}user").roles("USER").build();

		UserDetails admin = User.builder().username("admin").password("{noop}admin").roles("ADMIN").build();

		manager.createUser(user);
		manager.createUser(admin);

		return manager;
	}
		
	
}
