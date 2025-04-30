package com.github.marcelo_neuro.ms_pedido.controller.handlers.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidationErrorDTO extends CustomErrorDTO{

    private List<FieldMessageDTO> errors = new ArrayList<>();

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
