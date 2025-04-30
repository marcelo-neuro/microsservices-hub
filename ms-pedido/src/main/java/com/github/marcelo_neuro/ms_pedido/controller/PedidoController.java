package com.github.marcelo_neuro.ms_pedido.controller;

import com.github.marcelo_neuro.ms_pedido.dto.PedidoDTO;
import com.github.marcelo_neuro.ms_pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
