package com.biblioteca.Fachada;

import com.biblioteca.basicos.Acervo;
import com.biblioteca.basicos.Emprestimo;
import com.biblioteca.basicos.Usuario;
import com.biblioteca.dados.RepositorioEmprestimo;
import com.biblioteca.dados.RepositorioUsuario;
import com.biblioteca.negocio.ControladorUsuario;
import com.biblioteca.negocio.ControllerEmprestimo;

import java.util.List;

public class Fachada {

    private static Fachada instancia;

    private ControladorUsuario controladorUsuario;
    private ControllerEmprestimo controladorEmprestimo;

    private Fachada(){
        RepositorioUsuario repoUs = new RepositorioUsuario();
        RepositorioEmprestimo repoEm = new RepositorioEmprestimo();
        this.controladorUsuario=new ControladorUsuario(repoUs);
        this.controladorEmprestimo=new ControllerEmprestimo(repoEm);
    }

    public static Fachada getInstance() {
        if(instancia==null) {
            instancia =new Fachada();
        }
        return instancia;
    }


    public void cadastrarAluno(String Nome, String CPF, String anoNascimento, String matricula, String Curso){
        this.controladorUsuario.Cadastrar(Nome, CPF, anoNascimento, matricula, Curso);
    }

    public void cadastrarProfessor(String nome, String cpf, String nasc, String siape) {
        this.controladorUsuario.Cadastrar(nome, cpf, nasc, siape);
    }



    public void atualizar(String Nome, String CPF, String anoNascimento, String Matricula, String Curso){
        this.controladorUsuario.Atualizar(Nome, CPF, anoNascimento,Matricula, Curso);
    }

    public Usuario buscarUsuario (String CPF){
        return this.controladorUsuario.Buscar(CPF);
    }

    public List<Usuario> listarUsuario (){
        return this.controladorUsuario.Listar();
    }

    public void removerUsuario(String cpf) {
        this.controladorUsuario.Remover(cpf);
    }

    public Emprestimo realizarEmprestimo (Usuario usuario, Acervo item){
        return this.controladorEmprestimo.realizarEmprestimo(usuario, item);
    }

    public void finalizarDevolucao (String cpfUsuario) {
        this.controladorEmprestimo.finalizarDevolucao(cpfUsuario);
    }
}
