package com.github.marcelo_neuro.ms_pagamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marcelo_neuro.ms_pagamento.dto.PagamentoDTO;
import com.github.marcelo_neuro.ms_pagamento.service.PagamentoService;
import com.github.marcelo_neuro.ms_pagamento.service.exception.ResourceNotFoundException;
import com.github.marcelo_neuro.ms_pagamento.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagamentoService service;

    private PagamentoDTO pagamentoDTO;
    private Long existingId;
    private Long nonExistingId;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 100L;

        pagamentoDTO = Factory.createPagamentoDTO();

        //findAll
        List<PagamentoDTO> list = List.of(pagamentoDTO);
        Mockito.when(service.getAll()).thenReturn(list);

        // SERVICE
        //findById
        Mockito.when(service.getById(existingId)).thenReturn(pagamentoDTO);
        Mockito.when(service.getById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        //create
        Mockito.when(service.create(any())).thenReturn(pagamentoDTO);
        //update
        Mockito.when(service.updadte(eq(existingId), any())).thenReturn(pagamentoDTO);
        Mockito.when(service.updadte(eq(nonExistingId), any()))
                .thenThrow(ResourceNotFoundException.class);


    }

    @Test
    public void findAllShouldRetrunPagamentoDtoList() throws Exception {
        ResultActions result = mockMvc.perform(get("/pagamentos")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void getByIdShoudReturnPagamentoDTOWhenIdExists() throws Exception {
        ResultActions result = mockMvc.perform(get("/pagamentos/{id}", existingId)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.valor").exists());
        result.andExpect(jsonPath("$.status").exists());
    }

    @Test
    public void getByIdShouldThrowResourceNotFoundExceptionWhenIdNotExists() throws Exception {
        ResultActions result = mockMvc.perform(get("/pagamentos/{id}", nonExistingId)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void createShouldReturnPagamentoDTOCreated() throws Exception {
        PagamentoDTO dto = Factory.createNewPagamentoDTO();

        String jsonRequestBody = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/pagamentos")
                        .content(jsonRequestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.valor").exists())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.pedidoId").exists())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.pedidoId").exists())
                .andExpect(jsonPath("$.formaPagamentoId").exists());
    }

    @Test
    public void updateShouldReturnPagamentoDTOWhenIdExists() throws Exception {
        String jsonRequestBody = objectMapper.writeValueAsString(pagamentoDTO);

        mockMvc.perform(put("/pagamentos/{id}", existingId)
                        .content(jsonRequestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingId))
                .andExpect(jsonPath("$.valor").value(pagamentoDTO.getValor()))
                .andExpect(jsonPath("$.pedidoId").exists())
                .andExpect(jsonPath("$.formaPagamentoId").exists());
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDontExists() throws Exception {
        String jsonRequestBody = objectMapper.writeValueAsString(pagamentoDTO);

        mockMvc.perform(put("/pagamentos/{id}", nonExistingId)
                        .content(jsonRequestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
