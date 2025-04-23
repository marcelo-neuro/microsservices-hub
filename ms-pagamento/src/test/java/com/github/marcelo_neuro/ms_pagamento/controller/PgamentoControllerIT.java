package com.github.marcelo_neuro.ms_pagamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marcelo_neuro.ms_pagamento.dto.PagamentoDTO;
import com.github.marcelo_neuro.ms_pagamento.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PgamentoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingId;
    private Long nonExistingId;
    private PagamentoDTO dto;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 50L;

        dto = Factory.createNewPagamentoDTO();
    }

    @Test
    public void findAllShouldReturnListAllPagamentos() throws Exception {
        mockMvc.perform(get("/pagamentos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].id").value(1L))
                .andExpect(jsonPath("[0].nome").isString())
                .andExpect(jsonPath("[0].nome").value("Amadeus Mozart"))
                .andExpect(jsonPath("[5].status").value("CONFIRMADO"));

    }

    @Test
    public void findByIdShoudReturnPagamentoDTOWhenIdExists() throws Exception {
        mockMvc.perform(get("/pagamentos/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("nome").isString())
                .andExpect(jsonPath("nome").value("Amadeus Mozart"))
                .andExpect(jsonPath("status").value("CRIADO"));
    }

    @Test
    public void findByIdSouldThrowResourceNotFoundExceptionWhenIdDoesntExist() throws Exception {
        mockMvc.perform(get("/pagamentos/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createShoudReturnPagamentoDto() throws Exception {
        dto = Factory.createPagamentoDTO();

        JsonPathResultMatchers status = jsonPath("status");
        mockMvc.perform(post("/pagamentos")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("nome").value(dto.getNome()))
                .andExpect(jsonPath("status").value("CRIADO"));
    }

    @Test
    public void createShouldPersistPagamentoWithRequiredFields() throws Exception {
        dto = Factory.createNewPagamentoDTOWithRequiredFields();
        mockMvc.perform(post("/pagamentos")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("valor").value(dto.getValor()))
                .andExpect(jsonPath("status").value("CRIADO"))
                .andExpect(jsonPath("nome").isEmpty())
                .andExpect(jsonPath("validade").isEmpty());
    }

    @Test
    public void createShouldThrowsExceptionsWhenIvalidData() throws Exception {
        dto = Factory.createNewPagamentoDTOWithInvalidData();
        mockMvc.perform(post("/pagamentos")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void updateShouldUpdateAndReturnPagamentoDTOWhenIdExists() throws Exception {
        mockMvc.perform(put("/pagamentos", existingId)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("nome").value(dto.getNome()))
                .andExpect(jsonPath("valor").value(dto.getValor()))
                .andExpect(jsonPath("status").value("CRIADO"));
    }
}
