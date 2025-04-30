package com.github.marcelo_neuro.ms_pedido.service;

import com.github.marcelo_neuro.ms_pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

}
