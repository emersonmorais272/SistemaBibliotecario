package com.biblioteca.dados;

import com.biblioteca.basicos.Usuario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario {

    private final List<Usuario> ListaUsuarios = new ArrayList<>();

    public void Adicionar(Usuario novoCadastro){
        this.ListaUsuarios.add(novoCadastro);
        this.SalvarArquivo(ListaUsuarios);
    }

    public void Remover(Usuario novoCadastro){
        this.ListaUsuarios.remove(novoCadastro);
        this.SalvarArquivo(ListaUsuarios);
    }

    public List<Usuario> Listar(){
        return this.ListaUsuarios;
    }

    public Usuario Buscar(String CPF){
        for(Usuario u : ListaUsuarios){
            if(u.getCPF().equals(CPF)){
                return u;
            }
        }
        return null;
    }

    public Boolean existe(String CPF){
        for(Usuario u : ListaUsuarios){
            return u.getCPF().equals(CPF);
        }
        return false;
    }

    public void SalvarArquivo(List<Usuario> lista){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuario.dat"))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
