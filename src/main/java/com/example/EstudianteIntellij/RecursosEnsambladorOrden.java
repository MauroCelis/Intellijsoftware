package com.example.EstudianteIntellij;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;


import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class RecursosEnsambladorOrden implements ResourceAssembler<Ordenar, Resource<Ordenar>> {
    @Override
    public Resource<Ordenar> toResource(Ordenar order) {
        Resource<Ordenar> ordenrecursos= new Resource<>(order,
                linkTo(methodOn(ControladordeOrden.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(ControladordeOrden.class).all()).withRel("ordenados"));

        if (order.getStatus() == Status.EN_PROGRESO) {
            ordenrecursos.add(
                    linkTo(methodOn(ControladordeOrden.class)
                            .cancel(order.getId())).withRel("cancel"));
            ordenrecursos.add(
                    linkTo(methodOn(ControladordeOrden.class)
                            .complete(order.getId())).withRel("complete"));
        }

        return ordenrecursos;    }
}
