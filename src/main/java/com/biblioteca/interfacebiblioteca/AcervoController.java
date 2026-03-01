package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Acervo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class AcervoController {

    @FXML private TableView<Acervo> tabelaAcervo;
    @FXML private TableColumn<Acervo, String> colTitulo;
    @FXML private TableColumn<Acervo, Integer> colCodigo;

    @FXML
    public void initialize() {
        // esse négocio configura quais atributos da classe acervo vão em cada coluna
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        ObservableList<Acervo> lista = FXCollections.observableArrayList(
                Fachada.getInstance().listarAcervo()
        );

        tabelaAcervo.setItems(lista);
    }

    @FXML
    private void voltar() throws Exception {
        MainApp.carregarTela("menu.fxml", "Menu Principal");
    }
}