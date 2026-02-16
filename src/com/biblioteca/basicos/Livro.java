package com.biblioteca.basicos;

import com.biblioteca.negocio.TamanhoInvalidoException;

public class Livro extends Acervo{
    private String isbn;

    public Livro(String titulo, int codigo, boolean disponivel, int quantidade, String isbn){
        super(titulo, codigo, disponivel, quantidade);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn (String isbn) throws TamanhoInvalidoException{
        if(isbn.length() < 13){
            throw new TamanhoInvalidoException("O codigo ISBN deve possuir 13 numeros");
        }
        this.isbn = isbn;
    }

}
