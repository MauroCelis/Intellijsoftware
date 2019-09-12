package com.example.EstudianteIntellij;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
        @Slf4j
public class CargarDatabase {

    @Bean
    CommandLineRunner iniDatabase(EstudianteRepositorio repositorio){
        return args -> {
          log.info("Preloading "+repositorio.save(new Estudiante("Carlos","Dalvia","5")));
            log.info("Preloading "+repositorio.save(new Estudiante("Pedro","Prado","7")));

        };

    }
}
