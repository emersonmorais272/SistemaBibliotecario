package com.biblioteca.Fachada;

import com.biblioteca.basicos.Usuario;
import com.biblioteca.negocio.ControladorUsuario;

public class IUsuario {

    private final ControladorUsuario cl = new ControladorUsuario();

    public void cadastrarUsuario(String Nome, String CPF, String anoNascimento, String Matricula, String Curso){
        cl.Cadastrar(Nome, CPF, anoNascimento, Matricula, Curso);
    }

    public Usuario exibir(String CPF){
        return cl.Buscar(CPF);
    }
}
