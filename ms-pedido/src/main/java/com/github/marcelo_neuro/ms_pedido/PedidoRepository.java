package com.github.marcelo_neuro.ms_pedido;

import com.github.marcelo_neuro.ms_pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
