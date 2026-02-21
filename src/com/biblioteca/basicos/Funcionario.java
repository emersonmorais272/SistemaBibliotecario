package com.biblioteca.basicos;

import java.io.Serializable;

public class Funcionario extends Usuario implements Serializable {
    private int codigoAcesso;

    public Funcionario(int codigoAcesso, String nome, String CPF, String anoNascimento){
        super(nome, CPF, anoNascimento);
        this.codigoAcesso = codigoAcesso;
    }

    public int getCodigoAcesso() {
        return codigoAcesso;
    }

    public void setCodigoAcesso(int codigoAcesso) {
        this.codigoAcesso = codigoAcesso;
    }

    @Override
    public double calcularMulta(long diasAtraso) {
        return 0;
    }
    @Override
    public int getPrazoEmprestimo() {
        return 0;
    }


}
