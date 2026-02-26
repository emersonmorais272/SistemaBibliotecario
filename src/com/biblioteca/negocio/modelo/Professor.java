package com.biblioteca.negocio.modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Professor extends Usuario implements Serializable {
    private String siape;

    public Professor(String nome, String CPF, String anoNascimento, String siape){
        super(nome, CPF, anoNascimento);
        this.siape = siape;
    }

    @Override

    public double calcularMulta(long diasAtraso) {
        return diasAtraso*3.0;
    }

    public String getSiape() {

        return siape;
    }

    public void setSiape(String siape) {

        this.siape = siape;
    }

    @Override
    public String toString() {
        return  "\n-----------------------------\n" +
                "USUARIO: Professor: " + super.toString() +
                "SIAPE='" + this.siape +
                "-----------------------------";
    }

    public LocalDate calcularDevoluçao (){
        LocalDate datadevolucao;
        datadevolucao = LocalDate.now().plusDays(30);
        return datadevolucao;
    }
    public int getPrazoEmprestimo() {
        return 30;
    }
}
