package com.salesianostriana.dam.courserplanner.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    
    	registry.addRedirectViewController("/", "/principal");
        

        registry.addViewController("/principal").setViewName("principal");
        
        registry.addViewController("/principalAdmin").setViewName("principalAdmin");
      
        registry.addViewController("/acceso").setViewName("acceso");
 
    }
}
