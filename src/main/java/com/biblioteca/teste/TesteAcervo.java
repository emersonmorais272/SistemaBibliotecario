package com.biblioteca.teste;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Livro;
import com.biblioteca.negocio.modelo.Artigo;
import com.biblioteca.negocio.modelo.Acervo;
import java.util.List;

public class TesteAcervo {
    public static void main(String[] args) {

        Fachada fachada = Fachada.getInstance();

        try {
            System.out.println("--- Testando Cadastro de Itens ---");

            // 2. Criando um Livro e um Artigo
            Livro l1 = new Livro("Sacramentadora", "Brandon Sanderson", 299, true, 5, "1234567890123");
            Artigo a1 = new Artigo("IA na Educação", "João Silva", 232, true, 10, "Março", 15, "Revista Tech");

            // 3. Cadastrando via fachada
            fachada.cadastrarItem(l1);
            fachada.cadastrarItem(a1);
            System.out.println("Itens cadastrados com sucesso!");

            // 4. Testando a listagem (para ver se salvou e recuperou)
            System.out.println("\n--- Listagem do Acervo ---");
            List<Acervo> lista = fachada.listarAcervo();
            for (Acervo item : lista) {
                System.out.println("Código: " + item.getCodigo() + " | Título: " + item.getTitulo() + " | Autor: " + item.getAutor());
            }

            // 5. Testando a busca
            System.out.println("\n--- Testando Busca ---");
            Acervo buscado = fachada.buscarItem(101);
            if (buscado != null) {
                System.out.println("Encontrado: " + buscado.getTitulo());
            }

        } catch (Exception e) {

            System.err.println("Erro no teste: " + e.getMessage());
        }
    }
}