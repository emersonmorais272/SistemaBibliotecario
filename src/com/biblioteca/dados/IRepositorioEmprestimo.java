package com.biblioteca.dados;

import com.biblioteca.basicos.Emprestimo;
import java.util.List;

public interface IRepositorioEmprestimo {

    void adicionar(Emprestimo emprestimo);

    void remover(Emprestimo emprestimo);

    Emprestimo buscarPorCpf(String cpf);

    Emprestimo buscarPorNomeUsuario(String nomeUsuario);

    List<Emprestimo> listar();

}