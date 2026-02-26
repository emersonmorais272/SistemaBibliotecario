package com.biblioteca.dados;

import com.biblioteca.negocio.modelo.Usuario;
import java.util.List;

public interface IRepositorioUsuario {

    void Adicionar(Usuario novoCadastro);

    void Remover(Usuario u);

    void Atualizar(Usuario usuarioAtualizado);

    Usuario Buscar(String CPF);

    List<Usuario> Listar();

}