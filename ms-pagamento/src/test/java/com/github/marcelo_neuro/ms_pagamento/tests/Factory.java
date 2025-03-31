package com.github.marcelo_neuro.ms_pagamento.tests;

import com.github.marcelo_neuro.ms_pagamento.entity.Pagamento;
import com.github.marcelo_neuro.ms_pagamento.entity.Status;

import java.math.BigDecimal;

public class Factory {
    public static Pagamento createPagamento() {
        return new Pagamento(1L, BigDecimal.valueOf(32.90), "João Neve", "1265412478964521",
                "07/32", "585", Status.CRIADO, 1L, 2L);
    }
}
