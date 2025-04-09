package com.github.marcelo_neuro.ms_pagamento.dto;

import com.github.marcelo_neuro.ms_pagamento.entity.Pagamento;
import com.github.marcelo_neuro.ms_pagamento.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class PagamentoDTO {

    private Long id;
    @NotNull(message = "Campo obrigatório.")
    @Positive(message = "O valor do pagamento deve ser maior que 0.")
    private BigDecimal valor;
    @Size(max = 100, message = "Nome deve ter até 100 caracteres.")
    private String nome;
    @Size(max = 19, message = "Número do cartão deve ter até 19 caracteres.")
    private String numeroDoCartao;
    @Size(max = 5, message = "Validade do cartão deve ter até 5 caracteres.")
    private String validade;
    @Size(max = 3, min = 3, message = "Codigo de segurança deve ter 3 caracteres.")
    private String codigoDeSeguranca;
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull(message = "PedidoId é obrigatório")
    private Long pedidoId;
    @NotNull(message = "FormaPagamentoId é obrigatório")
    private Long formaPagamentoId;

    public PagamentoDTO(Pagamento entity) {
        this.id = entity.getId();
        this.valor = entity.getValor();
        this.nome = entity.getNome();
        this.numeroDoCartao = entity.getNumeroDoCartao();
        this.validade = entity.getValidade();
        this.codigoDeSeguranca = entity.getCodigoDeSeguranca();
        this.status = entity.getStatus();
        this.pedidoId = entity.getPedidoId();
        this.formaPagamentoId = entity.getFormaDePagamentoId();
    }

    public PagamentoDTO() {}

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public String getValidade() {
        return validade;
    }

    public String getCodigoDeSeguranca() {
        return codigoDeSeguranca;
    }

    public Status getStatus() {
        return status;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public Long getFormaPagamentoId() {
        return formaPagamentoId;
    }


}
