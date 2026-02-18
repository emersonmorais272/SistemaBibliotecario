package com.biblioteca.basicos;

import java.time.LocalDate;

public class Aluno extends Usuario{
    private String matricula;
    private String curso;

    public Aluno(String nome, String CPF, String anoNascimento, String matriculla, String curso){
        super(nome, CPF, anoNascimento);
        this.matricula = matricula;
        this.curso = curso;
    }

    @Override
    public double calcularMulta(long diasAtraso) {
        return diasAtraso*2.0;
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

    @Override
    public int getPrazoEmprestimo() {
        return 15;
    }
}
