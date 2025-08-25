package com.github.marcelo_neuro.ms_pagamento.controller;

import com.github.marcelo_neuro.ms_pagamento.dto.PagamentoDTO;
import com.github.marcelo_neuro.ms_pagamento.kafka.PagamentoPendenteProducer;
import com.github.marcelo_neuro.ms_pagamento.service.PagamentoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(path = "/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;
    @Autowired
    private PagamentoPendenteProducer pagamentoPendenteProducer;

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> create(@Valid @RequestBody PagamentoDTO requestDto) {
        PagamentoDTO responseDto = service.create(requestDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(responseDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> update(@PathVariable Long id,
                                               @Valid @RequestBody PagamentoDTO requestDto) {
        return ResponseEntity.ok(service.updadte(id, requestDto));
    }

    @PatchMapping("/{id}/confirmar")
    @CircuitBreaker(name = "atualizarPedido",
            fallbackMethod = "confirmarPagamentoFallBack")
    public void confirmarPagamentoPedido(@PathVariable
                                           @NotNull Long id) {

        service.confirmarPagamentoPedido(id);
    }

    public void confirmarPagamentoFallBack(Long id, Exception e) {
        service.alterarStatusPagamento(id);
        pagamentoPendenteProducer.enviarPagamentoPendente(id.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
