package com.biblioteca.negocio;

import com.biblioteca.negocio.exceptions.FormatoInvalidoException;
import com.biblioteca.negocio.exceptions.TamanhoInvalidoException;

import java.util.Scanner;

public class MetodosAuxiliares {

    public static boolean temNumero(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }
        return false;
    }

    public static boolean temLetra(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) return true;
        }
        return false;
    }

    public static String lerEntradaValidada(String pergunta, int tamanhoEsperado, Scanner sc) {
        boolean ehValido = false;
        String entrada = null;

        while (!ehValido) {
            System.out.println(pergunta);
            try {
                entrada = sc.nextLine();

                if(entrada.isBlank())
                    throw new FormatoInvalidoException("ENTRADA INVALIDA: O CAMPO NAO PODE ESTAR VAZIO");

                if (MetodosAuxiliares.temLetra(entrada))
                    throw new FormatoInvalidoException("ENTRADA INVÁLIDA: DEVE CONTER APENAS NÚMEROS");

                if (entrada.length() != tamanhoEsperado)
                    throw new TamanhoInvalidoException("TAMANHO INVÁLIDO: DEVE CONTER " + tamanhoEsperado + " CARACTERES");

                ehValido = true;
            } catch (FormatoInvalidoException | TamanhoInvalidoException e) {
                System.err.println("ERRO: " + e.getMessage());
                System.err.flush();
            }
        }
        return entrada;
    }

    public static String lerEntradaValidada(String pergunta, Scanner sc){
        boolean ehValido = false;
        String entrada = null;

        while (!ehValido){
            System.out.println(pergunta);
            try {
                entrada = sc.nextLine();

                if(entrada.isBlank())
                    throw new FormatoInvalidoException("ENTRADA INVALIDA: O CAMPO NAO PODE ESTAR VAZIO");

                if(MetodosAuxiliares.temNumero(entrada))
                    throw new FormatoInvalidoException("ENTRADA INVALIDA: DEVE CONTER APENAS LETRAS");

                ehValido = true;

            } catch (FormatoInvalidoException e) {
                System.err.println("Erro: " + e.getMessage());
                System.err.flush();
            }
        }
        return entrada;
    }
}

