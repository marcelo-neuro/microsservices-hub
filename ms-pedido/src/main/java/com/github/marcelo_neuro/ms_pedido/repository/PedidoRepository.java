package com.github.marcelo_neuro.ms_pedido.repository;

import com.github.marcelo_neuro.ms_pedido.entity.Pedido;
import com.github.marcelo_neuro.ms_pedido.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("""
            SELECT p FROM Pedido p
            LEFT JOIN FETCH p.items
            WHERE p.id = :id
            """)
    Pedido getPedidoByIdWithItems(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE Pedido p
            SET p.status = :status
            WHERE p = :pedido
            """)
    void updatePedido(Status status, Pedido pedido);
}
