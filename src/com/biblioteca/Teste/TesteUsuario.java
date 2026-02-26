package com.biblioteca.Teste;

import com.biblioteca.Fachada.Fachada;
import com.biblioteca.negocio.modelo.Usuario;
import com.biblioteca.negocio.modelo.Acervo;
import com.biblioteca.negocio.modelo.Livro;
import com.biblioteca.negocio.modelo.Emprestimo;

import java.util.List;
import java.util.Scanner;

import static com.biblioteca.negocio.MetodosAuxiliares.lerEntradaValidada;

public class TesteUsuario {
    public static void main(String[] args){

        Fachada fachada = Fachada.getInstance();
        List<Usuario> usuarios = fachada.listarUsuario();

        System.out.println(fachada.listarAcervo());

        if(fachada.listarUsuario() == null){
            System.out.println("Ainda nao ha usuarios cadastrados");
        } else {
            System.out.println(fachada.listarUsuario());
        }
        Scanner sc = new Scanner(System.in);
        String nome = null;
        String cpf = null;
        String anoN = null;
        String matricula = null;
        String curso = null;

        nome = lerEntradaValidada("Qual eh o nome?", sc);
        cpf = lerEntradaValidada("Qual eh o CPF", 11, sc);
        anoN = lerEntradaValidada("Qual eh o ano de nascimento", 4, sc);
        matricula = lerEntradaValidada("Qual eh a matricula", 8, sc);
        curso = lerEntradaValidada("Qual eh o curso matriculado?", sc);

        fachada.cadastrarAluno(nome, cpf, anoN, matricula, curso);
        Usuario user = fachada.buscarUsuario(cpf);

        if (user != null) {
            System.out.println("Usuário cadastrado: " + user.toString());

            // CORREÇÃO AQUI: Adicionado o parâmetro "Herbert Schildt" (Autor)
            // Agora são 6 parâmetros: Título, Autor, Código, Disponível, Quantidade, ISBN
            Acervo livro = new Livro("Java para Iniciantes", "Herbert Schildt", 101, true, 1, "9788573933758");

            System.out.println("\nStatus inicial do livro: " + (livro.isDisponivel() ? "Disponível" : "Indisponível"));

            try {
                System.out.println("Tentando realizar empréstimo...");
                Emprestimo emp = fachada.realizarEmprestimo(user, livro);

                System.out.println("Empréstimo realizado com sucesso!");
                System.out.println("Status do livro agora: " + (livro.isDisponivel() ? "Disponível" : "Indisponível"));

                // 3. Testar a Devolução
                System.out.println("\nFinalizando devolução para o CPF: " + cpf);
                fachada.finalizarDevolucao(cpf);
                System.out.println("Status do livro após devolução: " + (livro.isDisponivel() ? "Disponível" : "Indisponível"));

            } catch (Exception e) {
                System.err.println("ERRO NO PROCESSO: " + e.getMessage());
            }
        } else {
            System.err.println("Erro: Usuário não pôde ser cadastrado/encontrado.");
        }
    }
}