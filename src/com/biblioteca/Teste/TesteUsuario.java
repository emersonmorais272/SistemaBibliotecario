package com.biblioteca.Teste;

import com.biblioteca.Fachada.IUsuario;
import com.biblioteca.negocio.MetodosAuxiliares;
import com.biblioteca.negocio.exceptions.FormatoInvalidoException;
import com.biblioteca.negocio.exceptions.TamanhoInvalidoException;

import java.util.Scanner;

import static com.biblioteca.negocio.MetodosAuxiliares.lerEntradaValidada;

public class TesteUsuario {
    public static void main(String[] args){

        IUsuario fachada = new IUsuario();

        System.out.println(fachada.exibir("123456789").toString());


        Scanner sc = new Scanner(System.in);
        String nome = null;
        String cpf = null;
        String anoN = null;
        String matricula = null;
        String curso = null;

        nome = lerEntradaValidada("Qual eh o nome?", sc);

        cpf = lerEntradaValidada("Qual eh o CPF", 9, sc);

        anoN = lerEntradaValidada("Qual eh o ano de nascimento", 4, sc);

        matricula = lerEntradaValidada("Qual eh a matricula", 8, sc);

        curso = lerEntradaValidada("Qual eh o curso matriculado?", sc);

        fachada.cadastrarUsuario(nome, cpf, anoN, matricula, curso);

        System.out.println(fachada.exibir(cpf).toString());
    }
}

