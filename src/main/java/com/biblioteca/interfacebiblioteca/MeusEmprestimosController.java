package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Emprestimo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MeusEmprestimosController {

    @FXML private TableView<Emprestimo> tabelaEmprestimos;
    @FXML private TableColumn<Emprestimo, String> colTitulo;
    @FXML private TableColumn<Emprestimo, String> colDataEmp;
    @FXML private TableColumn<Emprestimo, String> colDataDev;

    private Fachada fachada = Fachada.getInstance();
    private DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {
        configurarColunas();
        carregarDados();
    }

    private void configurarColunas() {
        // Extrai o título do item que está dentro do empréstimo
        colTitulo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getItem().getTitulo()));

        // Formata as datas para o padrão brasileiro
        colDataEmp.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataEmprestimo().format(formatador)));

        colDataDev.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataPrevistaDevolucao().format(formatador)));
    }

    private void carregarDados() {
        // Recupera quem é o usuário logado no sistema
        String cpfLogado = MainApp.getUsuarioLogado().getCPF();

        // Busca todos os empréstimos e filtra apenas os do usuário logado
        List<Emprestimo> todos = fachada.listarEmprestimos();
        List<Emprestimo> meus = todos.stream()
                .filter(e -> e.getUsuario().getCPF().equals(cpfLogado))
                .collect(Collectors.toList());

        ObservableList<Emprestimo> dados = FXCollections.observableArrayList(meus);
        tabelaEmprestimos.setItems(dados);
    }

    @FXML
    private void voltar() throws Exception {
        MainApp.carregarTela("emprestimo.fxml", "Gerenciar Empréstimos");
    }
}
