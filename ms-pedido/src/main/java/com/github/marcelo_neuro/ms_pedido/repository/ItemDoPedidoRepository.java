package com.github.marcelo_neuro.ms_pedido.repository;

import com.github.marcelo_neuro.ms_pedido.entity.ItemDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, Long> {
}
