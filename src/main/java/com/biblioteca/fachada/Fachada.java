package com.biblioteca.fachada;

import com.biblioteca.negocio.modelo.Acervo;
import com.biblioteca.negocio.modelo.Emprestimo;
import com.biblioteca.negocio.modelo.Usuario;
import com.biblioteca.dados.RepositorioAcervo;
import com.biblioteca.dados.RepositorioEmprestimo;
import com.biblioteca.dados.RepositorioUsuario;
import com.biblioteca.negocio.ControllerUsuario;
import com.biblioteca.negocio.ControllerAcervo;
import com.biblioteca.negocio.ControllerEmprestimo;

import java.util.List;

public class Fachada {

    private static Fachada instancia;

    private ControllerUsuario controladorUsuario;
    private ControllerEmprestimo controladorEmprestimo;
    private ControllerAcervo controladorAcervo;

    private Fachada(){
        RepositorioUsuario repoUs = new RepositorioUsuario();
        RepositorioEmprestimo repoEm = new RepositorioEmprestimo();
        RepositorioAcervo repoAc = new RepositorioAcervo();

        this.controladorUsuario = new ControllerUsuario(repoUs);
        this.controladorEmprestimo = new ControllerEmprestimo(repoEm);
        this.controladorAcervo = new ControllerAcervo(repoAc);
    }

    public static Fachada getInstance() {
        if(instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }


    public void cadastrarItem(Acervo item) throws Exception {
        this.controladorAcervo.cadastrarItem(item);
    }

    public void removerItem(int codigo) throws Exception {
        this.controladorAcervo.removerItem(codigo);
    }

    public Acervo buscarItem(int codigo) {
        return this.controladorAcervo.buscarItem(codigo);
    }

    public void atualizarItem(Acervo item) throws Exception {
        this.controladorAcervo.atualizarItem(item);
    }

    public List<Acervo> listarAcervo() {
        return this.controladorAcervo.listarTudo();
    }


    public void cadastrarAluno(String nome, String cpf, String anoNasc, String matricula, String curso){
        this.controladorUsuario.Cadastrar(nome, cpf, anoNasc, matricula, curso);
    }

    public void cadastrarProfessor(String nome, String cpf, String nasc, String siape) {
        this.controladorUsuario.Cadastrar(nome, cpf, nasc, siape);
    }

    public void cadastrarFuncionario(String nome, String cpf, String anoNasc, int codigoAcesso) {
        this.controladorUsuario.Cadastrar(nome, cpf, anoNasc, codigoAcesso);
    }

    public void atualizarUsuario(String cpf, String nome, String anoNasc, String matricula, String curso){
        this.controladorUsuario.Atualizar(cpf, nome, anoNasc, matricula, curso);
    }

    public void atualizarUsuario(String cpf, String nome, String anoNasc, String SIAPE){
        this.controladorUsuario.Atualizar(cpf, nome, anoNasc, SIAPE);
    }

    public void atualizarUsuario(String cpf, String nome, String anoNasc, int codigoAcesso){
        this.controladorUsuario.Atualizar(cpf, nome, anoNasc, codigoAcesso);
    }
    public Usuario buscarUsuario(String cpf){
        return this.controladorUsuario.Buscar(cpf);
    }

    public Usuario buscarCodigo(int codigo) {
        return this.controladorUsuario.buscarPorCodigo(codigo);
    }

    public List<Usuario> listarUsuario(){
        return this.controladorUsuario.Listar();
    }

    public void removerUsuario(String cpf) {
        this.controladorUsuario.Remover(cpf);
    }



    public Emprestimo realizarEmprestimo(Usuario usuario, Acervo item) {
        // A fachada apenas solicita a operação; o Controller aplica prazos e validações
        return this.controladorEmprestimo.realizarEmprestimo(usuario, item);
    }

    public double finalizarDevolucao(String cpfUsuario) {
        return this.controladorEmprestimo.finalizarDevolucao(cpfUsuario);
    }
    public java.util.List<Emprestimo> listarEmprestimos() {
        return this.controladorEmprestimo.listarEmprestimos();
    }

}