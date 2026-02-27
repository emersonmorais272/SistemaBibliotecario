package com.biblioteca.Fachada;

import com.biblioteca.negocio.modelo.Acervo;
import com.biblioteca.negocio.modelo.Emprestimo;
import com.biblioteca.negocio.modelo.Usuario;
import com.biblioteca.dados.RepositorioAcervo; // Adicionado
import com.biblioteca.dados.RepositorioEmprestimo;
import com.biblioteca.dados.RepositorioUsuario;
import com.biblioteca.negocio.ControllerUsuario;
import com.biblioteca.negocio.ControllerAcervo; // Adicionado
import com.biblioteca.negocio.ControllerEmprestimo;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    // METODOS DO USUARIO

    public void cadastrarAluno(String Nome, String CPF, String anoNascimento, String matricula, String Curso){
        this.controladorUsuario.Cadastrar(Nome, CPF, anoNascimento, matricula, Curso);
    }

    public void cadastrarProfessor(String nome, String cpf, String nasc, String siape) {
        this.controladorUsuario.Cadastrar(nome, cpf, nasc, siape);
    }

    public void atualizarUsuario(String CPF, String Nome, String anoNascimento, String Matricula, String Curso){
        this.controladorUsuario.Atualizar(CPF, Nome, anoNascimento, Matricula, Curso);
    }

    public Usuario buscarUsuario (String CPF){
        return this.controladorUsuario.Buscar(CPF);
    }

    public Usuario buscarCodigo (int codigo) {
        return this.controladorUsuario.buscarPorCodigo(codigo);
    }

    public List<Usuario> listarUsuario (){
        return this.controladorUsuario.Listar();
    }

    public void removerUsuario(String cpf) {
        this.controladorUsuario.Remover(cpf);
    }

    // METODOS DE EMPRESTIMO





    public Emprestimo realizarEmprestimo(Usuario usuario, Acervo item) {

        int prazo = usuario.getPrazoEmprestimo();
        LocalDate dataPrevista = LocalDate.now().plusDays(prazo);


        return this.controladorEmprestimo.realizarEmprestimo(usuario, item, dataPrevista);
    }


    public double finalizarDevolucao(String cpfUsuario, LocalDate dataEntregaReal) {

        return this.controladorEmprestimo.finalizarDevolucao(cpfUsuario, dataEntregaReal);
    }
}