package com.github.marcelo_neuro.ms_pedido.dto;

import com.github.marcelo_neuro.ms_pedido.entity.ItemDoPedido;
import com.github.marcelo_neuro.ms_pedido.entity.Pedido;
import com.github.marcelo_neuro.ms_pedido.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoDTO {

    private Long id;
    @NotEmpty(message = "CPF requerido")
    @Size(min = 11, max = 14, message = "CPF deve conter de 11 a dígitos")
    private String cpf;
    @NotEmpty(message = "Nome equerido")
    @Size(min = 3, max = 100, message = "CPF deve conter de 11 a 1 dígitos")
    private String nome;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private StatusDTO status;
    @NotEmpty(message = "Deve conter ao menos um item.")
    private List<@Valid ItemDoPedidoDTO> itens = new ArrayList<>();
    private BigDecimal valorTotal;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.cpf = pedido.getCpf();
        this.nome = pedido.getNome();
        this.data = pedido.getData();
        this.status = new StatusDTO(pedido.getStatus());

        pedido.getItens().forEach((i) -> {
            itens.add(new ItemDoPedidoDTO(i));
        });
        pedido.calcularTotalPedido();
        this.valorTotal = pedido.getValorTotal();
    }

}
