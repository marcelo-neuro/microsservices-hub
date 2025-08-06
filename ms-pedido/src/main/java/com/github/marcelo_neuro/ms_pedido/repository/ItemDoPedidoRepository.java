package com.github.marcelo_neuro.ms_pedido.repository;

import com.github.marcelo_neuro.ms_pedido.entity.ItemDoPedido;
import com.github.marcelo_neuro.ms_pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, Long> {


    @Transactional
    @Modifying
    @Query("""
            DELETE FROM ItemDoPedido i
            WHERE i.pedido.id = :pedidoId
            """)
    void deleteByPedidoId(Long pedidoId);
}
