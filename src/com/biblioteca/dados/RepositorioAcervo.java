package com.biblioteca.dados;

import com.biblioteca.basicos.Acervo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAcervo implements IRepositorioAcervo {

    private List<Acervo> listaItens;
    private final String PATH = "acervo.dat";

    public RepositorioAcervo() {
        this.listaItens = new ArrayList<>();
        this.carregarArquivo();
    }

    @Override
    public void adicionar(Acervo item) {
        this.listaItens.add(item);
        this.salvarArquivo();
    }

    @Override
    public void remover(int codigo) {
        Acervo itemEncontrado = this.buscar(codigo);
        if (itemEncontrado != null) {
            this.listaItens.remove(itemEncontrado);
            this.salvarArquivo();
        }
    }

    @Override
    public void atualizar(Acervo item) {
        for (int i = 0; i < listaItens.size(); i++) {
            if (listaItens.get(i).getCodigo() == item.getCodigo()) {
                listaItens.set(i, item);
                this.salvarArquivo();
                break;
            }
        }
    }

    @Override
    public Acervo buscar(int codigo) {
        for (Acervo a : listaItens) {
            if (a.getCodigo() == codigo) {
                return a;
            }
        }
        return null;
    }

    @Override
    public List<Acervo> listar() {
        return this.listaItens;
    }

    private void salvarArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            oos.writeObject(this.listaItens);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo do acervo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarArquivo() {
        File arquivo = new File(PATH);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                this.listaItens = (List<Acervo>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao carregar arquivo do acervo: " + e.getMessage());
                this.listaItens = new ArrayList<>();
            }
        }
    }
}