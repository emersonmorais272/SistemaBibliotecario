package com.biblioteca.negocio.modelo;

import java.io.Serializable;

public abstract class Acervo implements Serializable {
    private String titulo;
    private String autor;
    private int codigo;
    private boolean disponivel = true;
    private int quantidade;

    public Acervo(String titulo, String autor, int codigo, boolean disponivel, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.disponivel = disponivel;
        this.quantidade = quantidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return  titulo + '\n' +
                "Autor:" + autor + '\n' +
                "Codigo:" + codigo +
                "\n" + (disponivel ? "Disponivel" : "Indisponivel") +
                "\nQuantidade:" + quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}