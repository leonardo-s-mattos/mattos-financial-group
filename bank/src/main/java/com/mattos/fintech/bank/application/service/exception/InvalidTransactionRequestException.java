package com.mattos.fintech.bank.application.service.exception;

public class InvalidTransactionRequestException extends RuntimeException{


    public InvalidTransactionRequestException(String message) {
        super(message);
    }
}
