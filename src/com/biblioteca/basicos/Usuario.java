package com.biblioteca.basicos;

public abstract class Usuario {
    private String Nome;
    private String CPF;
    private int idade;
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
        int anoAtual = 2026;
        this.idade = calcularIdade(Integer.parseInt(anoNascimento), anoAtual);
    }

    public String getCPF() {

        return CPF;
    }

    public void setCPF(String CPF) {

        this.CPF = CPF;
    }

    public int calcularIdade(int anoNascimento, int anoAtual){

        return anoAtual - anoNascimento;
    }
    public abstract double calcularMulta(long diasAtraso);
}