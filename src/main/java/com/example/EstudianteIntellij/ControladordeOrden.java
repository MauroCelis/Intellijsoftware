package com.example.EstudianteIntellij;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.criteria.Order;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ControladordeOrden {
    private final OrdenarRespositorio orderRepository;
    private final RecursosEnsambladorOrden assembler;

    ControladordeOrden(OrdenarRespositorio orderRepository, RecursosEnsambladorOrden assembler) {

        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    @GetMapping("/orders")
    Resources<Resource<Ordenar>> all() {

        List<Resource<Ordenar>> orders = orderRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(orders,
                linkTo(methodOn(ControladordeOrden.class).all()).withSelfRel());
    }

    @GetMapping("/orders/{id}")
    Resource<Order> one(@PathVariable Long id) {
        return assembler.toResource(
                orderRepository.findById(id)
                        .orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @PostMapping("/orders")
    ResponseEntity<Resource<Order>> newOrder(@RequestBody Ordenar order) {

        order.setStatus(Status.EN_PROGRESO);
        Ordenar nuevoOrden = orderRepository.save(order);

        return ResponseEntity
                .created(linkTo(methodOn(ControladordeOrden.class).one(nuevoOrden.getId())).toUri())
                .body(assembler.toResource(nuevoOrden));
    }
}
