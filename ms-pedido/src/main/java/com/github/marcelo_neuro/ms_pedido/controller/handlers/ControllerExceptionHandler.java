package com.github.marcelo_neuro.ms_pedido.controller.handlers;


import com.github.marcelo_neuro.ms_pedido.controller.handlers.dto.CustomErrorDTO;
import com.github.marcelo_neuro.ms_pedido.controller.handlers.dto.ValidationErrorDTO;
import com.github.marcelo_neuro.ms_pedido.service.exception.DatabaseException;
import com.github.marcelo_neuro.ms_pedido.service.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> handleResourceNotFound(ResourceNotFoundException e,
                                                                 HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomErrorDTO error = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorDTO> handleDatabase(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomErrorDTO error = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                           HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationErrorDTO error = new ValidationErrorDTO(Instant.now(), status.value(), "Dados inválidos",
                request.getRequestURI());

        e.getBindingResult().getFieldErrors().forEach(fe -> error.addError(fe.getField(), fe.getDefaultMessage()));
        return ResponseEntity.status(status).body(error);
    }
}
