package com.example.EstudianteIntellij;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EstudianteNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EstudianteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String estudianteNotFoundHandler(EstudianteNotFoundException ex){
        return ex.getMessage();
    }
}
