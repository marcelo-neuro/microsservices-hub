package com.github.marcelo_neuro.ms_pedido.controller.handlers.dto;

import java.util.Objects;

public class FieldMessageDTO {
    private String fieldName;
    private String message;

    public FieldMessageDTO() {
    }

    public FieldMessageDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldMessageDTO that = (FieldMessageDTO) o;
        return Objects.equals(fieldName, that.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName);
    }
}
