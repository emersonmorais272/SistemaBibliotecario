package com.biblioteca.basicos;

public abstract class Acervo {
    private String titulo;
    private int codigo;
    private boolean disponivel = true;
    private int quantidade;

    public Acervo(String titulo, int codigo, boolean disponivel, int quantidade){
        this.titulo = titulo;
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

    /// testando
    /// TESTE @

}
