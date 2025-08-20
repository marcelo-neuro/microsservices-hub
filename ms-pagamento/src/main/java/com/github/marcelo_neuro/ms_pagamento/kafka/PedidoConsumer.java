package com.github.marcelo_neuro.ms_pagamento.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PedidoConsumer {
    @KafkaListener(topics = "topicos-pedidos", groupId = "grupo-ms")
    public void consumerMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
