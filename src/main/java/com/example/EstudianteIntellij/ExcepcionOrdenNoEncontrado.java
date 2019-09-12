package com.example.EstudianteIntellij;

public class ExcepcionOrdenNoEncontrado extends  RuntimeException {
    ExcepcionOrdenNoEncontrado(Long id) {
        super("Estudiante no encontrado "+id);
    }
}
