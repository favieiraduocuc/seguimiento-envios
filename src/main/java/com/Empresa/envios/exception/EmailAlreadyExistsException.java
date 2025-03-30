package com.Empresa.envios.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public EmailAlreadyExistsException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}