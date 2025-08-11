package com.github.marcelo_neuro.ms_pedido.controller;

import com.github.marcelo_neuro.ms_pedido.dto.PedidoDTO;
import com.github.marcelo_neuro.ms_pedido.dto.StatusDTO;
import com.github.marcelo_neuro.ms_pedido.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping(path = "/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @RequestBody @Valid PedidoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(path = "/{id}/pago")
    public ResponseEntity<String> aprovarPagamento(@PathVariable @NotNull Long id) {
        service.aprovarPagamentoPedido(id);
        String msg = "Pagamento aprovado, aguarde confirmação do pagamento";
        return ResponseEntity.ok(msg);
    }

    @PutMapping(path = "/{id}/status")
    public ResponseEntity<PedidoDTO> updateStatus(@PathVariable @NotNull Long id, @RequestBody StatusDTO statusDto) {
        PedidoDTO dto = service.updatePedidoStatus(id, statusDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
