package com.biblioteca.basicos;

import java.io.Serializable;

public class Artigo extends Acervo implements Serializable {
    private String mesPublicacao;
    private int edicao;
    private String localPublicacao;

    public Artigo(String titulo, String autor, int codigo, boolean disponivel, int quantidade, String mesPublicacao, int edicao, String localPublicacao) {
        super(titulo, autor, codigo, disponivel, quantidade);
        this.mesPublicacao = mesPublicacao;
        this.edicao = edicao;
        this.localPublicacao = localPublicacao;
    }

    public String getMesPublicacao() {
        return mesPublicacao;
    }

    public void setMesPublicacao(String mesPublicacao) {
        this.mesPublicacao = mesPublicacao;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getLocalPublicacao() {
        return localPublicacao;
    }

    public void setLocalPublicacao(String localPublicacao) {
        this.localPublicacao = localPublicacao;
    }
}