package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Acervo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class AcervoComumController {

    @FXML private TableView<Acervo> tabelaAcervo;
    @FXML private TableColumn<Acervo, String> colTipo;
    @FXML private TableColumn<Acervo, String> colTitulo;
    @FXML private TableColumn<Acervo, String> colAutor;
    @FXML private TableColumn<Acervo, Number> colCodigo;
    @FXML private TableColumn<Acervo, Number> colQtd;

    private Fachada fachada = Fachada.getInstance();

    @FXML
    public void initialize() {
        configurarColunas();
        carregarAcervo();
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
            ObservableList<Acervo> dados = FXCollections.observableArrayList(itens);
            tabelaAcervo.setItems(dados);
        }
    }

    @FXML
    private void voltar() throws Exception {
        MainApp.carregarTela("menuUsuarioComum.fxml", "Menu Principal");
    }
}
