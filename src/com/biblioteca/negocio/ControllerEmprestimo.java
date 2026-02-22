package com.biblioteca.negocio;

import com.biblioteca.basicos.Emprestimo;
import com.biblioteca.basicos.Usuario;
import com.biblioteca.basicos.Acervo;
import com.biblioteca.dados.RepositorioEmprestimo;
import com.biblioteca.negocio.exceptions.EmprestimoNaoEncontradoException;
import com.biblioteca.negocio.exceptions.ItemIndisponivelException;


public class ControllerEmprestimo {


    private RepositorioEmprestimo repoEmprestimo;


    public ControllerEmprestimo(RepositorioEmprestimo repo) {
        this.repoEmprestimo = repo;
    }

    public void finalizarDevolucao (String  cpfUsuario) {

        Emprestimo emprestimo = this.repoEmprestimo.buscarPorCpf(cpfUsuario);

        if (emprestimo == null) {
            throw new EmprestimoNaoEncontradoException("Não há empréstimo ativo para este usuário.");
        }

        emprestimo.registrarDevolucao();

        emprestimo.getItem().setDisponivel(true);
    }

    public Emprestimo realizarEmprestimo(Usuario usuario, Acervo item) {

        if (!item.isDisponivel()) {

            throw new ItemIndisponivelException("O item selecionado não está disponível.");
        }

        int prazo = usuario.getPrazoEmprestimo();
        Emprestimo emprestimo = new Emprestimo(usuario, item, prazo);

        item.setDisponivel(false);

        this.repoEmprestimo.adicionar(emprestimo);

        return emprestimo;
    }
}

