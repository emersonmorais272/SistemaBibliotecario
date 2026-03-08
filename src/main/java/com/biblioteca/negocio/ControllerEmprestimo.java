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
            throw new EmprestimoNaoEncontradoException("Nenhum empréstimo ativo para este CPF.");
        }

        LocalDate hoje = LocalDate.now();
        LocalDate prevista = emprestimo.getDataPrevistaDevolucao();
        double multaTotal = 0;


        if (hoje.isAfter(prevista) && !emprestimo.isMultaPerdoada()) {
            long diasAtraso = java.time.temporal.ChronoUnit.DAYS.between(prevista, hoje);
            multaTotal = emprestimo.getUsuario().calcularMulta(diasAtraso);
        }


        emprestimo.getItem().setDisponivel(true);
        this.repoEmprestimo.remover(emprestimo);

        return multaTotal;
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
    public java.util.List<com.biblioteca.negocio.modelo.Emprestimo> listarEmprestimos() {
        return this.repoEmprestimo.listar();
    }

    public void perdoarMulta(Emprestimo emprestimo) {
        if (emprestimo != null) {
            emprestimo.setMultaPerdoada(true);
            this.repoEmprestimo.SalvarArquivo(this.repoEmprestimo.listar());
        }
    }
}

