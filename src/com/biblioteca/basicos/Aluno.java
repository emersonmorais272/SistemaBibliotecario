package com.biblioteca.basicos;

import java.time.LocalDate;

public class Aluno extends Usuario{
    private String matricula;

    public Aluno(String nome, String CPF, int idade, String matricula){
        super(nome, CPF, idade);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate calcularDevoluçao (){
        LocalDate datadevolucao;
        datadevolucao = LocalDate.now().plusDays(15);
        return datadevolucao;
    }
}
