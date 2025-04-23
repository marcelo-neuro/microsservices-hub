package com.github.marcelo_neuro.ms_pagamento.tests;

import com.github.marcelo_neuro.ms_pagamento.dto.PagamentoDTO;
import com.github.marcelo_neuro.ms_pagamento.entity.Pagamento;
import com.github.marcelo_neuro.ms_pagamento.entity.Status;

import java.math.BigDecimal;

public class Factory {
    public static Pagamento createPagamento() {
        return new Pagamento(1L, BigDecimal.valueOf(32.90), "João Neve", "1265412478964521",
                "07/32", "585", Status.CRIADO, 1L, 2L);
    }

    public static PagamentoDTO createPagamentoDTO() {
        return new PagamentoDTO(createPagamento());
    }

    public static PagamentoDTO createNewPagamentoDTO() {
        Pagamento pagamento = createPagamento();
        pagamento.setId(null);

        return new PagamentoDTO(pagamento);
    }

    public static PagamentoDTO createNewPagamentoDTOWithRequiredFields() {
        Pagamento pagamento = createPagamento();
        pagamento.setId(null);
        pagamento.setNome(null);
        pagamento.setValidade(null);
        pagamento.setCodigoDeSeguranca(null);
        pagamento.setNumeroDoCartao(null);
        
        return new PagamentoDTO(pagamento);
    }

    public static PagamentoDTO createNewPagamentoDTOWithInvalidData() {
        Pagamento pagamento = createPagamento();
        pagamento.setId(null);
        pagamento.setValor(BigDecimal.valueOf(-35.2));
        pagamento.setPedidoId(null);
        pagamento.setFormaDePagamentoId(null);
        return new PagamentoDTO(pagamento);
    }
}
