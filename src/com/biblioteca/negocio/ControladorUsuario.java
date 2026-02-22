package com.biblioteca.negocio;

import com.biblioteca.basicos.Aluno;
import com.biblioteca.basicos.Professor;
import com.biblioteca.basicos.Usuario;
import com.biblioteca.dados.RepositorioUsuario;
import com.biblioteca.negocio.exceptions.FormatoInvalidoException;
import com.biblioteca.negocio.exceptions.TamanhoInvalidoException;

public class ControladorUsuario {

    RepositorioUsuario repositorio = new RepositorioUsuario();

    public void Cadastrar(String Nome, String CPF, String anoNascimento, String Matricula, String Curso){

        if(MetodosAuxiliares.temNumero(Nome) || Nome.isBlank()){
            throw new FormatoInvalidoException("O nome é formado apenas por letras");
        }

        if(MetodosAuxiliares.temLetra(CPF) || CPF.isBlank()){
            throw new FormatoInvalidoException("O CPF deve conter apenas numeros");
        } else if(CPF.length() != 9){
            throw new TamanhoInvalidoException("O CPF deve conter 9 numeros");
        }

        if(anoNascimento.length() != 4 || !MetodosAuxiliares.temNumero(anoNascimento)){
            throw new TamanhoInvalidoException("O ano de nascimento deve conter 4 numeros");
        } else if(MetodosAuxiliares.temLetra(anoNascimento)){
            throw new FormatoInvalidoException("O ano de nascimento nao deve conter letras");
        }

        if(MetodosAuxiliares.temLetra(Matricula) || Matricula.isBlank()){
            throw new FormatoInvalidoException("A matricula deve conter apenas numeros");
        } else if(Matricula.length() != 8){
            throw new TamanhoInvalidoException("A matricula deve possuir 8 numeros");
        }

        if(MetodosAuxiliares.temNumero(Curso) || Nome.isBlank()){
            throw new FormatoInvalidoException("O Curso é formado apenas por letras");
        }

        Usuario novoUsuario = new Aluno(Nome.toLowerCase(), CPF, anoNascimento, Matricula, Curso);
        novoUsuario.setIdade(2026 - Integer.parseInt(anoNascimento));
        repositorio.Adicionar(novoUsuario);

    }

    public void Cadastrar(String Nome, String CPF, String anoNascimento, String SIAPE){

        if(MetodosAuxiliares.temNumero(Nome) || Nome.isBlank()){
            throw new FormatoInvalidoException("O nome é formado apenas por letras");
        }

        if(MetodosAuxiliares.temLetra(CPF) || CPF.isBlank()){
            throw new FormatoInvalidoException("O CPF deve conter apenas numeros");
        }

        if(anoNascimento.length() != 4 || !MetodosAuxiliares.temNumero(anoNascimento)){
            throw new TamanhoInvalidoException("O ano de nascimento deve conter 4 numeros");
        } else if(MetodosAuxiliares.temLetra(anoNascimento)){
            throw new FormatoInvalidoException("O ano de nascimento nao deve conter letras");
        }

        if(MetodosAuxiliares.temLetra(SIAPE) || SIAPE.isBlank()){
            throw new FormatoInvalidoException("A matricula deve conter apenas numeros");
        } else if(SIAPE.length() != 8){
            throw new TamanhoInvalidoException("A matricula deve possuir 8 numeros");
        }

        Usuario novoUsuario = new Professor(Nome, CPF, anoNascimento, SIAPE);
        novoUsuario.setIdade(2026 - Integer.parseInt(anoNascimento));
        repositorio.Adicionar(novoUsuario);

    }

    public String Remover(String CPF){
        Usuario u = repositorio.Buscar(CPF);
        if(u != null){
            String nome = u.getNome();
            repositorio.Remover(u);
            return (nome + " removido com sucesso!");
        }
        return "Usuario nao existe!";
    }

    public Usuario Buscar(String CPF){
        Usuario u = repositorio.Buscar(CPF);
        return u;
    }

}