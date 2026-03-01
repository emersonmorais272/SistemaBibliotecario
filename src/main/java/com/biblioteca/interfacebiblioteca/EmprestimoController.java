package com.biblioteca.interfacebiblioteca;

import javafx.fxml.FXML;

public class EmprestimoController {

    @FXML
    private void voltar() throws Exception {
        MainApp.carregarTela("menu.fxml", "Menu Principal");
    }
}