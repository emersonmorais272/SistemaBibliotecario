package com.biblioteca.dados;

import com.biblioteca.basicos.Usuario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class RepositorioUsuario {

    public void SalvarArquivo(List<Usuario> lista){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuario.dat"))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
