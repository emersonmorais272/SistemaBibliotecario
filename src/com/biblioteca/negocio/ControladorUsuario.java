package com.biblioteca.negocio;

import com.biblioteca.basicos.Aluno;
import com.biblioteca.basicos.Professor;
import com.biblioteca.basicos.Usuario;

public class ControladorUsuario {

    public Usuario Cadastrar(String Nome, String CPF, String anoNascimento, String Matricula, String Curso){

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

        if(MetodosAuxiliares.temLetra(Matricula) || Matricula.isBlank()){
            throw new FormatoInvalidoException("A matricula deve conter apenas numeros");
        } else if(Matricula.length() != 8){
            throw new TamanhoInvalidoException("A matricula deve possuir 8 numeros");
        }

        if(MetodosAuxiliares.temNumero(Curso) || Nome.isBlank()){
            throw new FormatoInvalidoException("O Curso é formado apenas por letras");
        }

        Usuario novoUsuario = new Aluno(Nome, CPF, anoNascimento, Matricula, Curso);

        return novoUsuario;

    }

    public Usuario Cadastrar(String Nome, String CPF, String anoNascimento, String SIAPE){

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

        return novoUsuario;

    }
}
