package com.biblioteca.Teste;

import com.biblioteca.Fachada.IUsuario;
import com.biblioteca.basicos.Usuario;

import java.util.Random;
import java.util.Scanner;

public class TesteUsuario {
    public static void main(String[] args){

        IUsuario fachada = new IUsuario();

        System.out.println(fachada.exibir("123456789").toString());


        Scanner sc = new Scanner(System.in);

        System.out.println("qual o nome?");
        String nome = sc.nextLine();

        System.out.println("Qual o cpf?");
        String cpf = sc.nextLine();

        System.out.println("Qual o ano de nascimento");
        String anoN = sc.nextLine();

        String matricula = sc.nextLine();

        System.out.println("Qual o Curso");
        String curso = sc.nextLine();

        fachada.cadastrarUsuario(nome, cpf, anoN, matricula, curso);

        System.out.println(fachada.exibir(cpf).toString());
    }
}

