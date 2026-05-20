package com.salesianostriana.dam.courserplanner.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity //esto sirve para activar la seguridad y que se apliquen las que indico
public class ConfiguracionSeguridad {

	@Bean
	SecurityFilterChain CadenaFiltrosSeguridad(HttpSecurity http) {
		
		//Aquí dentro lo que hacemos es decidir quien entra y quien no y a que puede entrar.
		http.authorizeHttpRequests(
				(authz)-> authz
					.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
					.requestMatchers("/", "/acceso", "/error").permitAll()
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest()
					.authenticated())
					.requestCache(cache -> {
					HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		            requestCache.setMatchingRequestParameterName(null);
		            cache.requestCache(requestCache);
		            	
		            })
					.formLogin(form -> form
							.loginPage("/acceso")
							.permitAll()
					);
				
		
			// Añadimos esto para poder acceder a la consola de H2
			// con Spring Security habilitado.
			http.csrf((csrf) -> {
				csrf.ignoringRequestMatchers("/h2/**");
			});
			http.headers((headers) -> headers.frameOptions((opts) -> opts.disable()));

			return http.build();	
		}
		
	
}
