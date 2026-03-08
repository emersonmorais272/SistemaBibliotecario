package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Acervo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class AcervoController {

    @FXML private TableView<Acervo> tabelaAcervo;
    @FXML private TableColumn<Acervo, String> colTipo;
    @FXML private TableColumn<Acervo, String> colTitulo;
    @FXML private TableColumn<Acervo, String> colAutor;
    @FXML private TableColumn<Acervo, Number> colCodigo;
    @FXML private TableColumn<Acervo, Number> colQtd;

    @FXML private TextField campoBusca;

    private ObservableList<Acervo> dados;

    private Fachada fachada = Fachada.getInstance();

    @FXML
    public void initialize() {
        configurarColunas();
        carregarAcervo();
        configurarBusca();
    }

    private void configurarColunas() {
        colTipo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));

        colTitulo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitulo()));

        colAutor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAutor()));

        colCodigo.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getCodigo()));

        colQtd.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getQuantidade()));
    }

    private void carregarAcervo() {
        List<Acervo> itens = fachada.listarAcervo();

        if (itens != null) {
            dados = FXCollections.observableArrayList(itens);
        }
    }

    private void configurarBusca() {

        FilteredList<Acervo> filtro = new FilteredList<>(dados, p -> true);

        campoBusca.textProperty().addListener((observable, oldValue, newValue) -> {

            filtro.setPredicate(acervo -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String busca = newValue.toLowerCase();

                if (acervo.getTitulo().toLowerCase().contains(busca)) {
                    return true;
                }

                if (acervo.getAutor().toLowerCase().contains(busca)) {
                    return true;
                }

                return false;
            });

        });

        SortedList<Acervo> sortedData = new SortedList<>(filtro);
        sortedData.comparatorProperty().bind(tabelaAcervo.comparatorProperty());

        tabelaAcervo.setItems(sortedData);
    }

    @FXML
    private void removerItemSelecionado() {

        Acervo selecionado = tabelaAcervo.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING,
                    "Atenção",
                    "Selecione um item na tabela para remover.");
            return;
        }

        try {
            fachada.removerItem(selecionado.getCodigo());
            mostrarAlerta(Alert.AlertType.INFORMATION,
                    "Sucesso",
                    "Item removido do acervo.");

            carregarAcervo();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR,
                    "Erro",
                    e.getMessage());
        }
        tabelaAcervo.refresh();
    }

    @FXML
    private void abrirCadastro() throws Exception {
        MainApp.carregarTela("cadastrar_acervo.fxml", "Cadastrar Novo Item");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void voltar() throws Exception {
        MainApp.carregarTela("menuFuncionario.fxml", "Menu Principal");
    }
}