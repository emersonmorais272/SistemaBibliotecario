package com.biblioteca.basicos;

import java.time.LocalDate;

public class Professor extends Usuario{
    private String siape;

    public Professor(String nome, String CPF, int idade, String siape){
        super(nome, CPF, idade);
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

    public LocalDate calcularDevoluçao (){
        LocalDate datadevolucao;
        datadevolucao = LocalDate.now().plusDays(30);
        return datadevolucao;
    }
}
