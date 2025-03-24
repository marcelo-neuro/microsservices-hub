package com.github.marcelo_neuro.ms_pagamento.controller.handlers.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ValidationErrorDTO extends CustomErrorDTO{

    private Set<FieldMessageDTO> errors = new HashSet<>();

    public ValidationErrorDTO(Instant timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public void addError(String field, String message) {
        errors.add(new FieldMessageDTO(field, message));
    }

    public List<FieldMessageDTO> getErrors() {
        return errors;
    }
}
