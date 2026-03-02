package com.biblioteca.dados;

import com.biblioteca.negocio.modelo.Aluno;
import com.biblioteca.negocio.modelo.Emprestimo;
import com.biblioteca.negocio.modelo.Professor;
import com.biblioteca.negocio.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEmprestimo implements IRepositorioEmprestimo {

    private final List<Emprestimo> listaEmprestimos = new ArrayList<>();

    public RepositorioEmprestimo() {
        this.CarregarArquivo();
    }

    public void adicionar (Emprestimo emprestimo) {
        listaEmprestimos.add(emprestimo);
        this.SalvarArquivo(listaEmprestimos);
        this.SalvarCSV(listaEmprestimos);
    }

    public void remover(Emprestimo emprestimo) {
        listaEmprestimos.remove(emprestimo);
        this.SalvarArquivo(listaEmprestimos);
    }

    public List<Emprestimo> listar() {
        return listaEmprestimos;
    }

    public Emprestimo buscarPorCpf(String cpf) {

        for (Emprestimo e : listaEmprestimos) {

            if (e.getUsuario().getCPF().equals(cpf)) {
                return e;
            }
        }
        return null;
    }

    public Emprestimo buscarPorNomeUsuario(String nomeUsuario) {

        for (Emprestimo e: listaEmprestimos) {
            if(e.getUsuario().getNome().equalsIgnoreCase(nomeUsuario)) {
                return e;
            }
        }

        return null;
    }

    public void SalvarCSV(List<Emprestimo> lista) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("DadosEmprestimo.csv"))) {
            writer.println("Emprestimos;Usuario;CPF;Codigo;Data de Emprestimo;Data Prevista Devolucao;");
            for (Emprestimo item : lista) {
               writer.println(item.getItem().getTitulo() +  ";" + item.getUsuario().getNome() + ";"  + item.getUsuario().getCPF() + ";" + item.getItem().getCodigo() + ";" + item.getDataEmprestimo() + ";" + item.getDataPrevistaDevolucao());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public void SalvarArquivo(List<Emprestimo> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("emprestimo.dat"))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void CarregarArquivo(){
        File arquivo = new File("emprestimo.dat");

        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                List<Emprestimo> listaLida = (List<Emprestimo>) ois.readObject();

                this.listaEmprestimos.clear();
                this.listaEmprestimos.addAll(listaLida);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao carregar os dados: " + e.getMessage());
            }
        }
    }

    }

