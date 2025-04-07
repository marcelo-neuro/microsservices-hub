package com.github.marcelo_neuro.ms_pagamento.repository;

import com.github.marcelo_neuro.ms_pagamento.entity.Pagamento;
import com.github.marcelo_neuro.ms_pagamento.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTest {

    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long contTotalPagamento;

    @BeforeEach
    public void setup() {
        existingId = 1L;
        nonExistingId = Long.MAX_VALUE;
        contTotalPagamento = 3L;
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        repository.deleteById(existingId);

        Assertions.assertFalse(repository.existsById(existingId));
    }

    @Test
    @DisplayName("Dado parâmetros válidos e id nulo, quando chamar Criar Pagamento, deve-se instanciar um Pagamento")
    public void givenValidParamsAndIdIsNull_whenCallCreatePagamento_thenInstantiatePagamento() {
        Pagamento pagamento = Factory.createPagamento();
        pagamento.setId(null);

        pagamento = repository.save(pagamento);

        Assertions.assertNotNull(pagamento.getId());
        Assertions.assertEquals(contTotalPagamento + 1, pagamento.getId());
    }

    @Test
    @DisplayName("Dado id quando chamar Busca pagamento, deve-se retornar um Pagamento")
    public void givenAnExistedId_whenCallFindById_thenReturnAPagamento() {
        Optional<Pagamento> pagamento = repository.findById(existingId);

        Assertions.assertTrue(pagamento.isPresent());
    }

    @Test
    @DisplayName("Dado id de entidade inexistente quando chamar Busca pagamento, deve-se retornar um Pagamento")
    public void givenAnExistedId_whenCallFindById_thenReturnEmpty() {
        Optional<Pagamento> pagamento = repository.findById(nonExistingId);
        Assertions.assertTrue(pagamento.isEmpty());
    }
}
