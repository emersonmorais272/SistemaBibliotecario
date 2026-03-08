package com.biblioteca.negocio;

import com.biblioteca.negocio.modelo.Aluno;
import com.biblioteca.negocio.modelo.Funcionario;
import com.biblioteca.negocio.modelo.Professor;
import com.biblioteca.negocio.modelo.Usuario;
import com.biblioteca.dados.RepositorioUsuario;
import com.biblioteca.negocio.exceptions.EntradaInvalidaException;
import com.biblioteca.negocio.exceptions.FormatoInvalidoException;
import com.biblioteca.negocio.exceptions.TamanhoInvalidoException;
import com.biblioteca.negocio.exceptions.UsuarioNaoEncontradoException;

import java.util.List;

public class ControllerUsuario {
    private RepositorioUsuario repoUsuario;

    public ControllerUsuario(RepositorioUsuario repo) {
        this.repoUsuario = repo;
    }

    private void VerificacaoUsuario(String Nome, String CPF, String anoNascimento) {
        if(MetodosAuxiliares.temNumero(Nome) || MetodosAuxiliares.temCaractereEspecial(Nome)){
            throw new FormatoInvalidoException("O nome é formado apenas por letras");
        }

        if(MetodosAuxiliares.temLetra(CPF) || CPF.isBlank()){
            throw new FormatoInvalidoException("O CPF deve conter apenas numeros");
        } else if(CPF.length() != 11){
            throw new TamanhoInvalidoException("O CPF deve conter 11 numeros");
        } else if(this.Buscar(CPF) != null){
            throw new EntradaInvalidaException("O CPF ja esta cadastrado");
        }

        VerificacaoIdade(anoNascimento);
    }

    private void VerificacaoIdade(String anoNascimento) {
        if(anoNascimento.length() != 4 || !MetodosAuxiliares.temNumero(anoNascimento)){
            throw new TamanhoInvalidoException("O ano de nascimento deve conter 4 numeros");
        } else if(MetodosAuxiliares.temLetra(anoNascimento)){
            throw new FormatoInvalidoException("O ano de nascimento nao deve conter letras");
        } else if(Integer.parseInt(anoNascimento) < 1900 || Integer.parseInt(anoNascimento) > 2026){
            throw new EntradaInvalidaException("Ano de nascimento invalido");
        }
    }

    public void Cadastrar(String Nome, String CPF, String anoNascimento, String Matricula, String Curso){

        VerificacaoUsuario(Nome, CPF, anoNascimento);

        VerificacaoAluno(Matricula, Curso);

        Usuario novoUsuario = new Aluno(Nome.toLowerCase(), CPF, anoNascimento, Matricula, Curso);
        novoUsuario.setIdade(2026 - Integer.parseInt(anoNascimento));
        this.repoUsuario.Adicionar(novoUsuario);

    }

    private void VerificacaoAluno(String Matricula, String Curso) {
        if(MetodosAuxiliares.temLetra(Matricula) || Matricula.isBlank()){
            throw new FormatoInvalidoException("A matricula deve conter apenas numeros");
        } else if(Matricula.length() != 8){
            throw new TamanhoInvalidoException("A matricula deve possuir 8 numeros");
        }

        if(MetodosAuxiliares.temNumero(Curso) || Curso.isBlank()){
            throw new FormatoInvalidoException("O Curso é formado apenas por letras");
        }
    }

    public void Cadastrar(String Nome, String CPF,  String anoNascimento, int codigoAcesso){
        VerificacaoUsuario(Nome, CPF, anoNascimento);
        if(String.valueOf(codigoAcesso).length() != 8)
            throw new TamanhoInvalidoException("A senha deve conter 8 caracteres");

        Usuario novoUsuario = new Funcionario(codigoAcesso, Nome, CPF, anoNascimento);
        novoUsuario.setIdade(2026 - Integer.parseInt(anoNascimento));
        this.repoUsuario.Adicionar(novoUsuario);

    }

    public void Cadastrar(String Nome, String CPF, String anoNascimento, String SIAPE){

        VerificacaoUsuario(Nome, CPF, anoNascimento);

        if(MetodosAuxiliares.temLetra(SIAPE) || SIAPE.isBlank()){
            throw new FormatoInvalidoException("A matricula deve conter apenas numeros");
        } else if(SIAPE.length() != 8){
            throw new TamanhoInvalidoException("A matricula deve possuir 8 numeros");
        }

        Usuario novoUsuario = new Professor(Nome, CPF, anoNascimento, SIAPE);
        novoUsuario.setIdade(2026 - Integer.parseInt(anoNascimento));
        this.repoUsuario.Adicionar(novoUsuario);

    }

    public void Remover(String CPF){
        Usuario u = this.repoUsuario.Buscar(CPF);
        if(u != null){
            this.repoUsuario.Remover(u);
        } else {
            throw new UsuarioNaoEncontradoException("CPF invalido! Nao foi possivel localizar o CPF");
        }
    }

    public Usuario Buscar(String CPF){
        Usuario u = this.repoUsuario.Buscar(CPF);
        return u;
    }

    public Funcionario buscarPorCodigo(int codigo) {
        for (Usuario u : this.repoUsuario.Listar()) {
            if (u instanceof Funcionario) {
                Funcionario f = (Funcionario) u;
                if (f.getCodigoAcesso() == codigo) {
                    return f;
                }
            }
        }
        return null;
    }

    public List<Usuario> Listar(){
        return this.repoUsuario.Listar();
    }

    public void Atualizar(String CPF, String Nome, String anoNascimento, String Matricula, String Curso) {
        if(this.repoUsuario.Buscar(CPF) == null){
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
        }

        if(MetodosAuxiliares.temNumero(Nome) || MetodosAuxiliares.temCaractereEspecial(Nome)){
            throw new FormatoInvalidoException("O nome é formado apenas por letras");
        }

        VerificacaoIdade(anoNascimento);

        VerificacaoAluno(Matricula, Curso);
        Usuario U = this.repoUsuario.Buscar(CPF);
        U = new Aluno(Nome, U.getCPF(), anoNascimento, Matricula, Curso);
        U.setIdade(2026 - Integer.parseInt(anoNascimento));
        this.repoUsuario.Atualizar(U);
    }

    public void Atualizar(String CPF, String Nome,  String anoNascimento, int codigoAcesso){
        if(this.repoUsuario.Buscar(CPF) == null){
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
        }

        if (MetodosAuxiliares.temNumero(Nome) || Nome.isBlank()) {
            throw new FormatoInvalidoException("O nome é formado apenas por letras");
        }

        VerificacaoIdade(anoNascimento);

        if(String.valueOf(codigoAcesso).length() != 8)
            throw new TamanhoInvalidoException("A senha deve conter 8 caracteres");

        Usuario U = this.repoUsuario.Buscar(CPF);
        U = new Funcionario(codigoAcesso, Nome, U.getCPF(), anoNascimento);
        U.setIdade(2026 - Integer.parseInt(anoNascimento));
        this.repoUsuario.Atualizar(U);

    }

    public void Atualizar(String CPF, String Nome, String anoNascimento, String siape) {
        if(this.repoUsuario.Buscar(CPF) == null){
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
        }

        if (MetodosAuxiliares.temNumero(Nome) || Nome.isBlank()) {
            throw new FormatoInvalidoException("O nome é formado apenas por letras");
        }

        VerificacaoIdade(anoNascimento);

        if (MetodosAuxiliares.temLetra(siape) || siape.isBlank()) {
            throw new FormatoInvalidoException("A matricula deve conter apenas numeros");
        } else if (siape.length() != 8) {
            throw new TamanhoInvalidoException("A matricula deve possuir 8 numeros");
        }

        Usuario U = this.repoUsuario.Buscar(CPF);
        U = new Professor(Nome, U.getCPF(), anoNascimento, siape);
        U.setIdade(2026 - Integer.parseInt(anoNascimento));
        this.repoUsuario.Atualizar(U);
    }

}