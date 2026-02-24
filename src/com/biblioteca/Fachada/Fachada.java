package com.biblioteca.Fachada;

import com.biblioteca.basicos.Usuario;
import com.biblioteca.negocio.ControladorUsuario;
import com.biblioteca.negocio.ControllerEmprestimo;

import java.util.List;

public class Fachada {

    private static Fachada instancia;

    private ControladorUsuario controladorUsuario;
    private ControllerEmprestimo controladorEmprestimo;

    private Fachada(){
        this.controladorUsuario=new ControladorUsuario();
        this.controladorEmprestimo=new ControllerEmprestimo(repoEmp);
    }

    private final ControladorUsuario cl = new ControladorUsuario();

    public void cadastrarUsuario(String Nome, String CPF, String anoNascimento, String Matricula, String Curso){
        cl.Cadastrar(Nome, CPF, anoNascimento, Matricula, Curso);
    }

    public void atualizar(String Nome, String CPF, String anoNascimento, String Matricula, String Curso){
        cl.Atualizar(Nome, CPF, anoNascimento,Matricula, Curso);
    }

    public Usuario exibir(String CPF){
        return cl.Buscar(CPF);
    }

    public List<Usuario> Listar(){
        return cl.Listar();
    }
}
