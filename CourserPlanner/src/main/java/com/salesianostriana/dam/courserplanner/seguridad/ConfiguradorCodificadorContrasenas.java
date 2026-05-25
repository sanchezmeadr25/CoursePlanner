package com.salesianostriana.dam.courserplanner.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

// Esta clase sirve para decir como queremos que se protejan las contraseñas en
// la base de datos. 
@Configuration
public class ConfiguradorCodificadorContrasenas {


	//Este metodo devuelve un objeto que lo que hace es coger la contraseña y encriptarla.
	@Bean
	public
	PasswordEncoder CodificadorContrasenas() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
