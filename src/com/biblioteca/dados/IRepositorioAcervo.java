package com.biblioteca.dados;

import com.biblioteca.negocio.modelo.Acervo;
import java.util.List;

public interface IRepositorioAcervo {

    void adicionar(Acervo item);

    void remover(int codigo);

    void atualizar(Acervo item);

    Acervo buscar(int codigo);

    List<Acervo> listar();

}