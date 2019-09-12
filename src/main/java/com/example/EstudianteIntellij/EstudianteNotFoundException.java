package com.example.EstudianteIntellij;

public class EstudianteNotFoundException extends  RuntimeException{

    EstudianteNotFoundException(Long id){
        super("No se logro encontrar el estudiante "+id);
    }
}
