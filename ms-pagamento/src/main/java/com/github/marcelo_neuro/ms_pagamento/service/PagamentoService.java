package com.github.marcelo_neuro.ms_pagamento.service;

import com.github.marcelo_neuro.ms_pagamento.dto.PagamentoDTO;
import com.github.marcelo_neuro.ms_pagamento.repository.PagamentoRepository;
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


}
