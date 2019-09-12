package com.example.EstudianteIntellij;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/ordenes")
    Resources<Resource<Ordenar>> all() {

        List<Resource<Ordenar>> orders = orderRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(orders,
                linkTo(methodOn(ControladordeOrden.class).all()).withSelfRel());
    }

    @GetMapping("/ordenes/{id}")
    Resource<Order> one(@PathVariable Long id) {
        return assembler.toResource(
                orderRepository.findById(id)
                        .orElseThrow(() -> new ControladordeOrden(id)));
    }

    @PostMapping("/ordenes")
    ResponseEntity<Resource<Order>> newOrder(@RequestBody Ordenar order) {

        order.setStatus(Status.EN_PROGRESO);
        Ordenar nuevoOrden = orderRepository.save(order);

        return ResponseEntity
                .created(linkTo(methodOn(ControladordeOrden.class).one(nuevoOrden.getId())).toUri())
                .body(assembler.toResource(nuevoOrden));
    }

    @DeleteMapping("/orders/{id}/cancel")
    ResponseEntity<ResourceSupport> cancel(@PathVariable Long id) {

        Ordenar order = orderRepository.findById(id).orElseThrow(() -> new ExcepcionOrdenNoEncontrado(id));

        if (order.getStatus() == Status.EN_PROGRESO) {
            order.setStatus(Status.CANCELADO);
            return ResponseEntity.ok(assembler.toResource(orderRepository.save(order)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Metodo no valido", "No es fue posible que puedas cambiar " + order.getStatus() + " status"));
    }

    @PutMapping("/orders/{id}/complete")
    ResponseEntity<ResourceSupport> complete(@PathVariable Long id) {

        Ordenar order = orderRepository.findById(id).orElseThrow(() -> new ExcepcionOrdenNoEncontrado(id));

        if (order.getStatus() == Status.EN_PROGRESO) {
            order.setStatus(Status.COMPLETADO);
            return ResponseEntity.ok(assembler.toResource(orderRepository.save(order)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Methodo no ejecutado", "No fue posible completar la orden" + order.getStatus() + " status"));
    }


}
