package com.salesianostriana.dam.courserplanner.exepciones;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class RecursoNoEncontradoExcepcion extends RuntimeException {
        public RecursoNoEncontradoExcepcion(String mensaje) {
            super(mensaje);
        }
    }

    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class CursoCompletoExcepcion extends RuntimeException {
        public CursoCompletoExcepcion(String mensaje) {
            super(mensaje);
        }
    }
}