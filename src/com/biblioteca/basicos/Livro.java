package com.biblioteca.basicos;

import com.biblioteca.negocio.exceptions.TamanhoInvalidoException;
import java.io.Serializable;

public class Livro extends Acervo implements Serializable {
    private String isbn;

    // DEIXE APENAS ESTE CONSTRUTOR
    public Livro(String titulo, String autor, int codigo, boolean disponivel, int quantidade, String isbn) {
        super(titulo, autor, codigo, disponivel, quantidade);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) throws TamanhoInvalidoException {
        if (isbn == null || isbn.length() < 13) {
            throw new TamanhoInvalidoException("O codigo ISBN deve possuir 13 numeros");
        }
        this.isbn = isbn;
    }
}