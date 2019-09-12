package com.example.EstudianteIntellij;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.Order;

public interface OrdenarRespositorio extends JpaRepository<Ordenar, Long> {
}
