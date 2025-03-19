package com.github.marcelo_neuro.ms_pagamento.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal valor;
    private String nome;
    private String numeroDoCartao;
    private String validade;
    private String codigoDeSeguranca;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private Long pedidoId;
    @Column(nullable = false)
    private Long formaPagamento;

    public Pagamento(Long id, BigDecimal valor, String nome, String numeroDoCartao,
                     String validade, String codigoDeSeguranca, Status status, Long pedidoId,
                     Long formaPagamento) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.numeroDoCartao = numeroDoCartao;
        this.validade = validade;
        this.codigoDeSeguranca = codigoDeSeguranca;
        this.status = status;
        this.pedidoId = pedidoId;
        this.formaPagamento = formaPagamento;
    }

    public Pagamento() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCodigoDeSeguranca() {
        return codigoDeSeguranca;
    }

    public void setCodigoDeSeguranca(String codigoDeSeguranca) {
        this.codigoDeSeguranca = codigoDeSeguranca;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Long formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
