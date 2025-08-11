package com.github.marcelo_neuro.ms_pagamento.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ms-pedido")
public interface PedidoClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/pedidos/{id}/pago")
    void updatePagamentoPedido(@PathVariable Long id);

}
