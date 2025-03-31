package com.github.marcelo_neuro.ms_pagamento.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PagamentoRepositoryTest {

    @Autowired
    private PagamentoRepository repository;

    

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        Long existingId = 1L;

        repository.deleteById(existingId);

        Assertions.assertFalse(repository.existsById(existingId));
    }
}
