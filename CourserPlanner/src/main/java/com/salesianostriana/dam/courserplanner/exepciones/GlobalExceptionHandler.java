package com.salesianostriana.dam.courserplanner.exepciones;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

	//Excepciom para cuando no se encuetnra algo
    @ExceptionHandler(GlobalException.RecursoNoEncontradoExcepcion.class)
    public String handleNotFound(GlobalException.RecursoNoEncontradoExcepcion ex, Model model) {
        model.addAttribute("errorTitulo", "Elemento no encontrado");
        model.addAttribute("errorMensaje", ex.getMessage());
        return "error"; 
    }

    //Excepcion para cuando el curso estal lleno
    @ExceptionHandler(GlobalException.CursoCompletoExcepcion.class)
    public String handleCursoCompleto(GlobalException.CursoCompletoExcepcion ex, Model model) {
        model.addAttribute("errorTitulo", "Error de inscripción");
        model.addAttribute("errorMensaje", ex.getMessage());
        return "error";
    }
}