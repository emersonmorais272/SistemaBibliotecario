package com.biblioteca.negocio.modelo;

import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.util.Objects;

public abstract class Usuario implements Serializable {
    @CsvBindByName(column = "Nome")
    private String Nome;
    @CsvBindByName(column = "CPF")
    private String CPF;
    @CsvBindByName(column = "Idade")
    private int idade;
    @CsvBindByName(column = "Ano_Nascimento")
    private String anoNascimento;

    public Usuario(String nome, String CPF, String anoNascimento) {
        Nome = nome;
        this.CPF = CPF;
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {

        return Nome;
    }

    public void setNome(String nome) {

        Nome = nome;
    }

    public String getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(String anoNascimento) {

        this.anoNascimento = anoNascimento;
    }

    public int getIdade() {

        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCPF() {

        return CPF;
    }

    public void setCPF(String CPF) {

        this.CPF = CPF;
    }

    @Override
    public String toString() {
        return  "Nome='" + Nome + '\'' +
                ", CPF='" + CPF + '\'' +
                ", idade=" + idade +
                ", anoNascimento='" + anoNascimento + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(CPF, usuario.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(CPF);
    }

    public abstract double calcularMulta(long diasAtraso);

    public abstract int getPrazoEmprestimo();
}