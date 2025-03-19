package com.github.marcelo_neuro.ms_pagamento.repository;

import com.github.marcelo_neuro.ms_pagamento.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
