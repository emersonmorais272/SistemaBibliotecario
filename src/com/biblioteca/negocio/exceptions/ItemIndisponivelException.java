package com.biblioteca.negocio.exceptions;

public class ItemIndisponivelException extends RuntimeException {

    public ItemIndisponivelException(String mensagem) {

        super(mensagem);
    }
}
