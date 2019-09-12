package com.example.EstudianteIntellij;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class EstudianteResourceAssembler implements ResourceAssembler<Estudiante, Resource> {
    @Override
    public Resource toResource(Estudiante estudiante){

        return new Resource<>(estudiante,
                linkTo(methodOn(EstudianteControlador.class).one(estudiante.getId())).withSelfRel(),
                linkTo(methodOn(EstudianteControlador.class).all()).withRel("estudiantes"));



    }

}
