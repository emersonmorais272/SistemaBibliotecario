package com.biblioteca.dados;

import com.biblioteca.basicos.Usuario;
import java.util.List;

public interface IRepositorioUsuario {

    void Adicionar(Usuario novoCadastro);

    void Remover(Usuario u);

    Usuario Buscar(String CPF);

    List<Usuario> Listar();

}