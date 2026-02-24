package com.biblioteca.Teste;

import com.biblioteca.Fachada.Fachada;
import com.biblioteca.basicos.Usuario;
import com.biblioteca.basicos.Acervo;
import com.biblioteca.basicos.Livro;
import com.biblioteca.basicos.Emprestimo;
import java.util.List;
import java.util.Scanner;

import static com.biblioteca.negocio.MetodosAuxiliares.lerEntradaValidada;

public class TesteUsuario {
    public static void main(String[] args){

        Fachada fachada = Fachada.getInstance();
        List<Usuario> usuarios = fachada.listarUsuario();

        for(Usuario u : usuarios){
            System.out.println(u.toString());
        }
        ///System.out.println(fachada.exibir("123456789").toString());


        Scanner sc = new Scanner(System.in);
        String nome = null;
        String cpf = null;
        String anoN = null;
        String matricula = null;
        String curso = null;

        nome = lerEntradaValidada("Qual eh o nome?", sc);

        cpf = lerEntradaValidada("Qual eh o CPF", 9, sc);

        anoN = lerEntradaValidada("Qual eh o ano de nascimento", 4, sc);

        matricula = lerEntradaValidada("Qual eh a matricula", 8, sc);

        curso = lerEntradaValidada("Qual eh o curso matriculado?", sc);

        fachada.cadastrarAluno(nome, cpf, anoN, matricula, curso);
        Usuario user = fachada.buscarUsuario(cpf);
        System.out.println("Usuário cadastrado: " + user.toString());

        System.out.println(fachada.buscarUsuario(cpf).toString());

        Acervo livro = new Livro("Java para Iniciantes", 101, true, 1, "9788573933758");
        System.out.println("\nStatus inicial do livro: " + (livro.isDisponivel() ? "Disponível" : "Indisponível"));

        try {
            System.out.println("Tentando realizar empréstimo...");
            Emprestimo emp = fachada.realizarEmprestimo(user, livro);

            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Data Prevista: " + emp.getUsuario().getPrazoEmprestimo() + " dias a partir de hoje.");
            System.out.println("Status do livro agora: " + (livro.isDisponivel() ? "Disponível" : "Indisponível"));

            // 3. Testar a Devolução
            System.out.println("\nFinalizando devolução para o CPF: " + cpf);
            fachada.finalizarDevolucao(cpf);
            System.out.println("Status do livro após devolução: " + (livro.isDisponivel() ? "Disponível" : "Indisponível"));

        } catch (Exception e) {
            System.err.println("ERRO NO PROCESSO: " + e.getMessage());
        }

    }
}

