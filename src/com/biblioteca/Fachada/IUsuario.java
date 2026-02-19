package com.biblioteca.Fachada;

import com.biblioteca.basicos.Usuario;
import com.biblioteca.negocio.ControladorUsuario;

public class IUsuario {

    private ControladorUsuario cl = new ControladorUsuario();

    public Usuario cadastrarUsuario(String Nome, String CPF, String anoNascimento, String Matricula, String Curso){
        return cl.Cadastrar(Nome, CPF, anoNascimento, Matricula, Curso);
    }
}
