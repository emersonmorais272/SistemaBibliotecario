package com.biblioteca.Teste;

import com.biblioteca.Fachada.Fachada;
import com.biblioteca.negocio.modelo.Usuario;
import com.biblioteca.negocio.modelo.Acervo;
import com.biblioteca.negocio.modelo.Livro;
import com.biblioteca.negocio.modelo.Emprestimo;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.biblioteca.negocio.MetodosAuxiliares.lerEntradaValidada;

public class TesteUsuario {
    public static void main(String[] args) {
        Fachada fachada = Fachada.getInstance();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- SISTEMA BIBLIOTECARIO UFAPE ---");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Realizar Emprestimo");
            System.out.println("3. Devolver Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");

            int opcaoMenu = sc.nextInt();
            sc.nextLine(); // Limpa buffer

            switch (opcaoMenu) {
                case 1:
                    System.out.println("\n--- Novo Cadastro ---");
                    String nome = lerEntradaValidada("Nome:", sc);
                    String cpf = lerEntradaValidada("CPF (11 digitos):", 11, sc);
                    String anoN = lerEntradaValidada("Ano Nascimento:", 4, sc);
                    String mat = lerEntradaValidada("Matricula:", 8, sc);
                    String curso = lerEntradaValidada("Curso:", sc);
                    fachada.cadastrarAluno(nome, cpf, anoN, mat, curso);
                    System.out.println("Usuario cadastrado com sucesso.");
                    break;

                case 2:
                    System.out.print("\nInforme o CPF do usuario: ");
                    String cpfBusca = sc.nextLine();
                    Usuario user = fachada.buscarUsuario(cpfBusca);

                    if (user != null) {
                        List<Acervo> catalogo = fachada.listarAcervo();
                        System.out.println("\n--- ACERVO DISPONIVEL ---");
                        for (int i = 0; i < catalogo.size(); i++) {
                            Acervo item = catalogo.get(i);
                            String status = item.isDisponivel() ? "[Disponivel]" : "[Indisponivel]";
                            System.out.println((i + 1) + ". " + status + " " + item.getTitulo());
                        }

                        System.out.print("Selecione o numero do livro: ");
                        int escolhaLivro = sc.nextInt();
                        sc.nextLine();

                        if (escolhaLivro > 0 && escolhaLivro <= catalogo.size()) {
                            Acervo selecionado = catalogo.get(escolhaLivro - 1);
                            try {
                                fachada.realizarEmprestimo(user, selecionado);
                                LocalDate dataDevolucao = LocalDate.now().plusDays(user.getPrazoEmprestimo());
                                System.out.println("Emprestimo realizado.");
                                System.out.println("DATA DE DEVOLUCAO: " + dataDevolucao.format(formatador));
                            } catch (Exception e) {
                                System.err.println("Erro: " + e.getMessage());
                            }
                        }
                    } else {
                        System.err.println("Usuario nao encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Informe o CPF para devolucao: ");
                    String cpfDev = sc.nextLine();

                    System.out.print("Informe a data de entrega (dd/MM/yyyy): ");
                    String dataInput = sc.nextLine();
                    LocalDate dataEntregaReal = LocalDate.parse(dataInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    try {
                        // Envia a data digitada para o controlador calcular a multa
                        double multaTotal = fachada.finalizarDevolucao(cpfDev, dataEntregaReal);

                        if (multaTotal > 0) {
                            System.out.printf("Devolucao realizada com atraso. Valor da multa: R$ %.2f%n", multaTotal);
                        } else {
                            System.out.println("Devolucao no prazo. Sem multa.");
                        }
                    } catch (Exception e) {
                        System.err.println("Erro: " + e.getMessage());
                    }
                    break;

                case 0:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
        System.out.println("Sistema encerrado.");
    }
}