package com.github.marcelo_neuro.ms_pagamento.service;

import com.github.marcelo_neuro.ms_pagamento.dto.PagamentoDTO;
import com.github.marcelo_neuro.ms_pagamento.entity.Pagamento;
import com.github.marcelo_neuro.ms_pagamento.entity.Status;
import com.github.marcelo_neuro.ms_pagamento.repository.PagamentoRepository;
import com.github.marcelo_neuro.ms_pagamento.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    public PagamentoDTO create(PagamentoDTO dto) {
        Pagamento entity = new Pagamento();
        copyDtoToEntity(dto, entity);
        entity.setStatus(Status.CRIADO);

        entity = repository.save(entity);

        return new PagamentoDTO(entity);
    }

    @Transactional
    public PagamentoDTO updadte(Long id, PagamentoDTO dto) {
        try {
            Pagamento entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity.setStatus(dto.getStatus());

            entity = repository.save(entity);

            return new PagamentoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado, id:" + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entidade não encontrada id:" + id);
        }
        repository.deleteById(id);
    }

    private void copyDtoToEntity(PagamentoDTO dto, Pagamento entity) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setValidade(dto.getValidade());
        entity.setValor(dto.getValor());
        entity.setCodigoDeSeguranca(dto.getCodigoDeSeguranca());
        entity.setNumeroDoCartao(dto.getNumeroDoCartao());
        entity.setPedidoId(dto.getPedidoId());
        entity.setFormaDePagamentoId(dto.getFormaPagamentoId());
    }
}
