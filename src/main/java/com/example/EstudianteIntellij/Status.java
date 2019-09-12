package com.example.EstudianteIntellij;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
enum Status {
    EN_PROGRESO,
    COMPLETADO,
    CANCELADO;
}
