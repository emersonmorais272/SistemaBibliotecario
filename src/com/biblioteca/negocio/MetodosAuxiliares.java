package com.biblioteca.negocio;

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
}
