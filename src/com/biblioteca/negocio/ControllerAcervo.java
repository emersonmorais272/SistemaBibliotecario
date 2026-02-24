package com.biblioteca.negocio;

import com.biblioteca.basicos.Acervo;
import com.biblioteca.dados.IRepositorioAcervo;
import java.util.List;

public class ControllerAcervo {

    private IRepositorioAcervo repo;

    public ControllerAcervo(IRepositorioAcervo repositorio) {
        this.repo = repositorio;
    }

    public void cadastrarItem(Acervo item) throws Exception {
        if (item == null) {
            throw new Exception("O item não pode ser nulo.");
        }

        if (item.getTitulo() == null || item.getTitulo().isEmpty()) {
            throw new Exception("O título do item deve ser preenchido.");
        }

        if (repo.buscar(item.getCodigo()) != null) {
            throw new Exception("Erro: Já existe um item com o código " + item.getCodigo());
        }

        repo.adicionar(item);
    }

    public void removerItem(int codigo) throws Exception {
        Acervo item = repo.buscar(codigo);

        if (item == null) {
            throw new Exception("Erro: Item não encontrado para remoção.");
        }

        if (!item.isDisponivel()) {
            throw new Exception("Não é possível remover um item que está emprestado.");
        }

        repo.remover(codigo);
    }

    public void atualizarItem(Acervo item) throws Exception {
        if (item == null) {
            throw new Exception("Item inválido.");
        }

        if (repo.buscar(item.getCodigo()) == null) {
            throw new Exception("Erro: Item não existe para ser atualizado.");
        }

        repo.atualizar(item);
    }

    public Acervo buscarItem(int codigo) {
        return repo.buscar(codigo);
    }

    public List<Acervo> listarTudo() {
        return repo.listar();
    }

}