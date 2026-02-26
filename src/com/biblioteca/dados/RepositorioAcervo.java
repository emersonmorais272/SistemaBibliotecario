package com.biblioteca.dados;

import com.biblioteca.negocio.modelo.Acervo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAcervo implements IRepositorioAcervo {

    private List<Acervo> listaItens;
    private final String PATH = "acervo.dat";

    public RepositorioAcervo() {
        this.listaItens = new ArrayList<>();
        this.carregarArquivo();
        inicializarAcervoPadrao();
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
    private void inicializarAcervoPadrao() {

        try {

            // livros cadastrados

            if (buscar(1) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "A Hora da estrela",
                        "Clarice Lispector",
                        1,
                        true,
                        5,
                        "9788532508126"
                ));
            }

            if (buscar(2) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "Noites Brancas",
                        "Fiódor Dostoiévski",
                        2,
                        true,
                        3,
                        "9788573263350"
                ));
            }

            if (buscar(3) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "A Metamorfose",
                        "Franz Kafka",
                        3,
                        true,
                        4,
                        "9786555985900"
                ));
            }

            if (buscar(4) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "A Redoma de Vidro",
                        "Sylvia Plath",
                        4,
                        true,
                        6,
                        "9788525068460"
                ));
            }

            if (buscar(5) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "O Processo",
                        "Franz Kafka",
                        5,
                        true,
                        3,
                        "978853907438"
                ));
            }

            if (buscar(6) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "Água Viva",
                        "Clarice Lispector",
                        6,
                        true,
                        2,
                        "9786555320213"
                ));
            }

            if (buscar(7) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "Jantar Secreto",
                        "Raphael Montes",
                        7,
                        true,
                        4,
                        "9788535928358"
                ));
            }

            if (buscar(8) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "O Avesso da pele",
                        "Jeferson Tenório",
                        8,
                        true,
                        5,
                        "9788535933390"
                ));
            }

            if (buscar(9) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "Capitães da Areia",
                        "Jorge Amado",
                        9,
                        true,
                        3,
                        "9788535914061"
                ));
            }

            if (buscar(10) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "Felicidade Clandestina",
                        "Clarice Lispector",
                        10,
                        true,
                        2,
                        "9788532531735"
                ));
            }

            if (buscar(11) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "Quarto de Despejo",
                        "Carolina Maria de Jesus",
                        11,
                        true,
                        5,
                        "9788508171279"
                ));
            }

            if (buscar(12) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "Vidas Secas",
                        "Graciliano Ramos",
                        12,
                        true,
                        8,
                        "9788501114785"
                ));
            }

            if (buscar(13) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "Drácula",
                        "Bram Stoker",
                        13,
                        true,
                        3,
                        "9788595201569"
                ));
            }

            if (buscar(14) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Livro(
                        "Mulherzinhas",
                        "LouisaMay Alcott",
                        14,
                        true,
                        2,
                        "9788544002124"
                ));
            }

            // artigos cadastrados

            if (buscar(16) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Inteligência Artificial na Educação",
                        "Samyle Alves",
                        16,
                        true,
                        5,
                        "Março",
                        12,
                        "Revista UFAPE"
                ));
            }

            if (buscar(17) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Segurança em Sistemas Distribuídos",
                        "Letícia de Melo Sobral",
                        17,
                        true,
                        3,
                        "Junho",
                        8,
                        "Revista UFAPE"
                ));
            }

            if (buscar(18) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Manguebeat: Diversidade na Música Pernambucana",
                        "José Teles",
                        18,
                        true,
                        3,
                        "Março",
                        1,
                        "Revista Continente (PE)"
                ));
            }

            if (buscar(19) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "O Movimento Armorial e a Identidade Cultural Nordestina",
                        "Ariano Suassuna",
                        19,
                        true,
                        2,
                        "Junho",
                        2,
                        "Revista Brasileira de Cultura"
                ));
            }

            if (buscar(20) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Cinema Novo: Estética da Fome",
                        "Glauber Rocha",
                        20,
                        true,
                        4,
                        "Abril",
                        3,
                        "Revista Civilização Brasileira"
                ));
            }

            if (buscar(21) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "A Dança Popular em Pernambuco: Frevo e Maracatu",
                        "Roberto Pereira",
                        21,
                        true,
                        3,
                        "Fevereiro",
                        4,
                        "Revista Brasileira de Estudos da Dança"
                ));
            }

            if (buscar(22) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Os Sertões e a Formação do Brasil Moderno",
                        "Euclides da Cunha",
                        22,
                        true,
                        2,
                        "Agosto",
                        5,
                        "Revista do Instituto Histórico e Geográfico Brasileiro"
                ));
            }

            if (buscar(23) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Inteligência Artificial e Sociedade",
                        "Emerson Morais",
                        23,
                        true,
                        5,
                        "Outubro",
                        6,
                        "Revista UFAPE"
                ));
            }

            if (buscar(24) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Tecnologia e Inclusão Digital no Brasil",
                        "Demi Getschko",
                        24,
                        true,
                        4,
                        "Setembro",
                        7,
                        "Revista CGI.br"
                ));
            }

            if (buscar(25) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Avanços na Medicina Tropical no Brasil",
                        "Carlos Chagas",
                        25,
                        true,
                        3,
                        "Maio",
                        8,
                        "Memórias do Instituto Oswaldo Cruz"
                ));
            }

            if (buscar(26) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "A Importância da Fiocruz para a Saúde Pública",
                        "Nísia Trindade Lima",
                        26,
                        true,
                        2,
                        "Julho",
                        9,
                        "Cadernos de Saúde Pública"
                ));
            }

            if (buscar(27) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Literatura Brasileira Contemporânea: Novas Vozes",
                        "Regina Dalcastagnè",
                        27,
                        true,
                        4,
                        "Novembro",
                        10,
                        "Revista Estudos de Literatura Brasileira Contemporânea"
                ));
            }

            if (buscar(28) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Ciência no Brasil: Desafios e Perspectivas",
                        "José Goldemberg",
                        28,
                        true,
                        3,
                        "Março",
                        11,
                        "Revista Ciência Hoje"
                ));
            }

            if (buscar(29) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "O Frevo como Patrimônio Cultural Imaterial",
                        "Cláudia Valença",
                        29,
                        true,
                        2,
                        "Dezembro",
                        12,
                        "Revista do IPHAN"
                ));
            }

            if (buscar(30) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Recife: Polo Tecnológico do Nordeste",
                        "Silvio Meira",
                        30,
                        true,
                        5,
                        "Agosto",
                        13,
                        "Revista Porto Digital"
                ));
            }

            if (buscar(31) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "A Música Popular Brasileira e suas Transformações",
                        "Hermano Vianna",
                        31,
                        true,
                        3,
                        "Abril",
                        14,
                        "Revista Brasileira de Ciências Sociais"
                ));
            }

            if (buscar(32) == null) {
                listaItens.add(new com.biblioteca.negocio.modelo.Artigo(
                        "Educação, Cultura e Sociedade no Nordeste Brasileiro",
                        "Paulo Freire",
                        32,
                        true,
                        4,
                        "Janeiro",
                        15,
                        "Revista Educação & Sociedade"
                ));
            }
            salvarArquivo();

        } catch (Exception e) {
            System.out.println("Erro ao inicializar acervo padrão: " + e.getMessage());
        }
    }
}