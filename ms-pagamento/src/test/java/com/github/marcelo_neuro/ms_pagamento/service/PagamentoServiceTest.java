package com.github.marcelo_neuro.ms_pagamento.service;

import com.github.marcelo_neuro.ms_pagamento.dto.PagamentoDTO;
import com.github.marcelo_neuro.ms_pagamento.entity.Pagamento;
import com.github.marcelo_neuro.ms_pagamento.repository.PagamentoRepository;
import com.github.marcelo_neuro.ms_pagamento.service.exception.ResourceNotFoundException;
import com.github.marcelo_neuro.ms_pagamento.tests.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService service;

    @Mock
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;

    private Pagamento pagamento;
    private PagamentoDTO pagamentoDTO;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 200L;

        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);
        Mockito.doNothing().when(repository).deleteById(existingId);

        pagamento = Factory.createPagamento();
        pagamentoDTO = new PagamentoDTO(pagamento);

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(pagamento));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(pagamento);
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(pagamento);
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("delete deve fazer nada ao deletar.")
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> service.delete(existingId));
    }

    @Test
    @DisplayName("delete deve lançar uma ResourceNotFoundException ao deletar Id não existente")
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDontExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete(nonExistingId));
    }

    @Test
    @DisplayName("getById deve retornar um PagamentoDTO preenchido")
    public void getByIdShoulfReturnPagamentoDTO() {
        PagamentoDTO dto = service.getById(existingId);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getId(), existingId);
        Assertions.assertEquals(dto.getValor(), pagamento.getValor());
    }

    @Test
    @DisplayName("getById deve lançar uma ResourseNotFoundException quando id não exstir")
    public void getByIdShouldThrowResourceNotFoundExceptionWhenIdDontExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.getById(nonExistingId));
    }

    @Test
    @DisplayName("create deve retornar pagamento")
    public void createShouldReturnPagamentoWhenPagamentoCreaed() {
        PagamentoDTO dto = service.create(pagamentoDTO);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getId(), pagamento.getId());
    }

    @Test
    public void updateShouldRetrurnPagamentoDTOWhenIdExists() {
        PagamentoDTO dto = service.updadte(pagamento.getId(), pagamentoDTO);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getId(), existingId);
        Assertions.assertEquals(dto.getValor(), pagamento.getValor());
    }
}
