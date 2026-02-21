package com.biblioteca.dados;

import com.biblioteca.basicos.Emprestimo;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEmprestimo {

    private List<Emprestimo> listaEmprestimos;

    public RepositorioEmprestimo() {
        this.listaEmprestimos=new ArrayList<>();
    }

    public void adicionar (Emprestimo emprestimo) {
        listaEmprestimos.add(emprestimo);
    }

    public void remover(Emprestimo emprestimo) {
        listaEmprestimos.remove(emprestimo);
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
}
