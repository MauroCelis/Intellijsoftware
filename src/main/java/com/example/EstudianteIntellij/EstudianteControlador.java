package com.example.EstudianteIntellij;

import org.springframework.hateoas.Resource;

import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
class EstudianteControlador {

    private final EstudianteRepositorio repositorio;
    private final  EstudianteResourceAssembler assembler;

    EstudianteControlador(EstudianteRepositorio repositorio,EstudianteResourceAssembler assembler){
        this.repositorio=repositorio;
        this.assembler=assembler;
    }

    @GetMapping("/estudiantes")
    Resources <Resource<Estudiante>> all(){
            List<Resource<Estudiante>>  estudiantes=repositorio.findAll().stream().
                    map(assembler::toResource).collect(Collectors.toList());
            return new Resources<>(estudiantes,
                    linkTo(methodOn(EstudianteControlador.class).all()).withSelfRel());
    }

    @PostMapping("/estudiantes")
    Estudiante nuevoEstudiante(@RequestBody Estudiante nuevoEstudiante){
        return repositorio.save(nuevoEstudiante);
    }

    @GetMapping("/estudiantes/{id}")
    Resource<Estudiante> one(@PathVariable Long id){


        Estudiante estudiante=repositorio.findById(id)
                .orElseThrow(()->new EstudianteNotFoundException(id));
        return new Resource<>(estudiante,
                linkTo(methodOn(EstudianteControlador.class).one(id)).withSelfRel(),
                linkTo(methodOn(EstudianteControlador.class).all()).withRel("estudiantes")
                );
    }

    @PutMapping("/estudiantes/{id}")
    Estudiante reemplazarEstudiante(@RequestBody Estudiante nuevoEstudiante, @PathVariable Long id){

        return repositorio.findById(id)
                .map(estudiante -> {
                    estudiante.setNombre(nuevoEstudiante.getNombre());
                    estudiante.setApellido(nuevoEstudiante.getApellido());
                    return repositorio.save(estudiante);
                }).orElseGet(()->{
                    nuevoEstudiante.setId(id);
                    return  repositorio.save(nuevoEstudiante);

                });
    }

    @DeleteMapping("/estudiantes/{id}")
    void borrarEstudiantes(@PathVariable Long id){
        repositorio.deleteById(id);
    }



}
