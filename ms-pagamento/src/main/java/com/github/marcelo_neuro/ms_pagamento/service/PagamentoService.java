package com.github.marcelo_neuro.ms_pagamento.service;

import com.github.marcelo_neuro.ms_pagamento.dto.PagamentoDTO;
import com.github.marcelo_neuro.ms_pagamento.repository.PagamentoRepository;
import com.github.marcelo_neuro.ms_pagamento.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Transactional(readOnly = true)
    public List<PagamentoDTO>  getAll() {
        return repository.findAll()
                .stream()
                .map(PagamentoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public PagamentoDTO getById(Long id) {
        return new PagamentoDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado id: " + id)));
    }
}
