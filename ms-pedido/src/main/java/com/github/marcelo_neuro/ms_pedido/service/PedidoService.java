package com.github.marcelo_neuro.ms_pedido.service;

import com.github.marcelo_neuro.ms_pedido.dto.PedidoDTO;
import com.github.marcelo_neuro.ms_pedido.repository.PedidoRepository;
import com.github.marcelo_neuro.ms_pedido.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(PedidoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id) {
        return new PedidoDTO(repository.findById(id)
                .orElseThrow(() -> {
                   return new ResourceNotFoundException("Entidade não encontrada, id: " + id);
                }));
    }
}
