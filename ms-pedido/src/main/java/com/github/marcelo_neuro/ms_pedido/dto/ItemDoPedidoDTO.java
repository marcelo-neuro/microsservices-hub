package com.github.marcelo_neuro.ms_pedido.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemDoPedidoDTO {

    @NotNull(message = "Quantidade requerida")
    @Positive(message = "Quantidade deve ser positivo")
    private Integer quantidade;
    @NotEmpty(message = "Descrição requeriada")
    private String descricao;
    @NotNull(message = "Valor unitário requerida")
    @Positive(message = "Valor unitário deve ser positivo")
    private BigDecimal valorUnitario;

}
