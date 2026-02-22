package com.biblioteca.dados;

import com.biblioteca.basicos.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario {

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
