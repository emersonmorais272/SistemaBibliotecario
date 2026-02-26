package com.biblioteca.dados;

import com.biblioteca.negocio.modelo.Usuario;
import com.biblioteca.negocio.exceptions.UsuarioNaoEncontradoException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario implements IRepositorioUsuario {

    private final List<Usuario> ListaUsuarios = new ArrayList<>();

    public RepositorioUsuario() {
        this.CarregarArquivo();
    }

    public void Adicionar(Usuario novoCadastro){
        this.ListaUsuarios.add(novoCadastro);
        this.SalvarArquivo(ListaUsuarios);
    }

    public void Remover(Usuario u){
        this.ListaUsuarios.remove(u);
        this.SalvarArquivo(ListaUsuarios);
    }

    public void Atualizar(Usuario usuarioAtualizado){
        boolean encontrou = false;

        for(int i = 0; i < this.ListaUsuarios.size(); i++){
            if(this.ListaUsuarios.get(i).getCPF().equals(usuarioAtualizado.getCPF())){
                this.ListaUsuarios.set(i, usuarioAtualizado);
                encontrou = true;
                break;
            }
        }

        if(encontrou){
            this.SalvarArquivo(ListaUsuarios);
        } else {
            throw new UsuarioNaoEncontradoException("Nao foi possivel atualiazer o usuario: CPF NAO ENCONTRADO!");
        }
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

    public void SalvarArquivo(List<Usuario> lista){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuario.dat"))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void CarregarArquivo() {
        File arquivo = new File("usuario.dat");

        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                List<Usuario> listaLida = (List<Usuario>) ois.readObject();

                this.ListaUsuarios.clear();
                this.ListaUsuarios.addAll(listaLida);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao carregar os dados: " + e.getMessage());
            }
        }
    }


}
