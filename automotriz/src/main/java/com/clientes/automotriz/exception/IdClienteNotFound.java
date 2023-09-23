package com.clientes.automotriz.exception;

public class IdClienteNotFound extends RuntimeException{
    private static final String ERROR_MESSAGE = "Cliente no se encuentra en nuestros registros";

    public IdClienteNotFound() {
        super(String.format(ERROR_MESSAGE));
    }
}
