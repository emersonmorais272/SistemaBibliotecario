package com.biblioteca.negocio.exceptions;

public class EmprestimoNaoEncontradoException extends RuntimeException{

    public EmprestimoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
