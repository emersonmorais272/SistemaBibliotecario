package com.biblioteca.negocio;

import com.biblioteca.negocio.modelo.Emprestimo;
import com.biblioteca.negocio.modelo.Usuario;
import com.biblioteca.negocio.modelo.Acervo;
import com.biblioteca.dados.RepositorioEmprestimo;
import com.biblioteca.negocio.exceptions.EmprestimoNaoEncontradoException;
import com.biblioteca.negocio.exceptions.ItemIndisponivelException;

import java.time.LocalDate;


public class ControllerEmprestimo {


    private RepositorioEmprestimo repoEmprestimo;

    public ControllerEmprestimo(RepositorioEmprestimo repo) {
        this.repoEmprestimo = repo;
    }


    public double finalizarDevolucao(String cpfUsuario) {

        Emprestimo emprestimo = this.repoEmprestimo.buscarPorCpf(cpfUsuario);

        if (emprestimo == null) {
            return 0;
        }

        LocalDate dataEntregaReal = LocalDate.now();
        LocalDate prevista = emprestimo.getDataDevolucao();
        double multaTotal = 0;


        if (dataEntregaReal.isAfter(prevista)) {
            long diasAtraso = java.time.temporal.ChronoUnit.DAYS.between(prevista, dataEntregaReal);

            multaTotal = emprestimo.getUsuario().calcularMulta(diasAtraso);
        }


        emprestimo.getItem().setDisponivel(true);
        this.repoEmprestimo.remover(emprestimo);

        return multaTotal;
    }

    public Emprestimo realizarEmprestimo(Usuario usuario, Acervo item, LocalDate dataPrevista) {

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

