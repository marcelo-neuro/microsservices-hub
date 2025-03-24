package com.github.marcelo_neuro.ms_pagamento.service.exception;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
}
