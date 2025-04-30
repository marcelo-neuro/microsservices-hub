package com.github.marcelo_neuro.ms_pedido.controller;

import com.github.marcelo_neuro.ms_pedido.dto.PedidoDTO;
import com.github.marcelo_neuro.ms_pedido.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findALl() {
        List<PedidoDTO> dtos = service.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping( path = "/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
        PedidoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> save(@RequestBody @Valid PedidoDTO dto) {

        dto = service.save(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return  ResponseEntity.created(uri).body(dto);
    }
}
