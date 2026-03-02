package com.biblioteca.dados;

import com.biblioteca.negocio.modelo.Aluno;
import com.biblioteca.negocio.modelo.Professor;
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
        this.SalvarCSV(ListaUsuarios);
    }

    public void Remover(Usuario u){
        this.ListaUsuarios.remove(u);
        this.SalvarArquivo(ListaUsuarios);
    }

    public void Atualizar(Usuario usuarioAtualizado){
        boolean encontrou = false;

        for(int i = 0; i < this.ListaUsuarios.size(); i++){
            if(this.ListaUsuarios.get(i).getCPF().equals(usuarioAtualizado.getCPF())){
                String temp = this.ListaUsuarios.get(i).getCPF();
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

    public void SalvarCSV(List<Usuario> lista) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("DadosUsuario.csv"))) {
            writer.println("Tipo;Nome;CPF;Idade;Matricula/SIAPE");
            for (Usuario item : lista) {
                if(item instanceof Aluno){
                    writer.println(item.getClass().getSimpleName() + ";" + item.getNome() + ";" + item.getCPF() + ";" + item.getIdade() + ";" + ((Aluno) item).getMatricula() + ";");
                } else if(item instanceof Professor){
                    writer.println(item.getClass().getSimpleName() + ";" + item.getNome() + ";" + item.getCPF() + ";" + item.getIdade() + ";" + ((Professor) item).getSiape() + ";");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
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
