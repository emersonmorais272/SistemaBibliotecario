package com.biblioteca.basicos;

public abstract class Usuario {
    private String Nome;
    private String CPF;
    private int idade;
    private int anoNascimento;

    public Usuario(String nome, String CPF, int idade) {
        Nome = nome;
        this.CPF = CPF;
        this.idade = idade;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        int anoAtual = 2026;
        this.idade = calcularIdade(anoNascimento, anoAtual);
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
}
