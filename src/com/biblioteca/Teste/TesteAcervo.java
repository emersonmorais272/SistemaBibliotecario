package com.biblioteca.Teste;

import com.biblioteca.Fachada.Fachada;
import com.biblioteca.basicos.Livro;
import com.biblioteca.basicos.Artigo;
import com.biblioteca.basicos.Acervo;
import java.util.List;

public class TesteAcervo {
    public static void main(String[] args) {
        // 1. Pegamos a instância da Fachada (Singleton)
        Fachada fachada = Fachada.getInstance();

        try {
            System.out.println("--- Testando Cadastro de Itens ---");

            // 2. Criando um Livro e um Artigo
            Livro l1 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 101, true, 5, "1234567890123");
            Artigo a1 = new Artigo("IA na Educação", "João Silva", 202, true, 10, "Março", 15, "Revista Tech");

            // 3. Cadastrando via Fachada
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
            // Se você rodar o teste duas vezes sem apagar o arquivo,
            // ele vai cair aqui porque o código já existe!
            System.err.println("Erro no teste: " + e.getMessage());
        }
    }
}