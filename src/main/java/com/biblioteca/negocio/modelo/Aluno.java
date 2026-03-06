package com.biblioteca.negocio.modelo;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.time.LocalDate;

public class Aluno extends Usuario implements Serializable {
    @CsvBindByName(column = "Matricula/SIAPE/CO_Acesso")
    private String matricula;
    @CsvBindByName(column = "Curso")
    private String curso;

    public Aluno(String nome, String CPF, String anoNascimento, String matricula, String curso){
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDate calcularDevoluçao (){
        LocalDate datadevolucao;
        datadevolucao = LocalDate.now().plusDays(15);
        return datadevolucao;
    }

    @Override
    public String toString() {
        return  "\n-----------------------------\n" +
                "USUARIO: Aluno: " + super.toString() +
                "matricula='" + matricula + '\'' +
                ", curso='" + curso + '\n' +
                "-----------------------------";
    }

    @Override
    public int getPrazoEmprestimo() {
        return 15;
    }
}
