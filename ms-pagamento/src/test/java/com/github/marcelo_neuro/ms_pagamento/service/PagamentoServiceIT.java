package com.github.marcelo_neuro.ms_pagamento.service;

import com.github.marcelo_neuro.ms_pagamento.dto.PagamentoDTO;
import com.github.marcelo_neuro.ms_pagamento.entity.Pagamento;
import com.github.marcelo_neuro.ms_pagamento.repository.PagamentoRepository;
import com.github.marcelo_neuro.ms_pagamento.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class PagamentoServiceIT {

    @Autowired
    private PagamentoService service;
    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExixstingId;
    private Long countTotPagamentos;

    @BeforeEach
    void setup() throws Exception {

        existingId = 1L;
        nonExixstingId = 100L;
        countTotPagamentos = 6L;
    }

    @Test
    public void deleteShouldDeleteWhenIdExists() {
        service.delete(existingId);
        Assertions.assertEquals(countTotPagamentos - 1L, repository.count());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundEsceptionWhenIdDoesntExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete(nonExixstingId));
    }

    @Test
    public void findAllShouldReturnPagamentoDTOList() {

        List<PagamentoDTO> result = service.getAll();

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countTotPagamentos, result.size());
        Assertions.assertEquals(Double.valueOf(35.55), result.get(0).getValor().doubleValue());
        Assertions.assertEquals("Amadeus Mozart", result.get(0).getNome());
        Assertions.assertEquals("Bartolomeu Pac", result.get(1).getNome());
        Assertions.assertNull(result.get(5).getNome());
    }
}
