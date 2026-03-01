package com.biblioteca.interfacebiblioteca;

import javafx.fxml.FXML;

public class UsuarioController {

    @FXML
    private void voltar() throws Exception {
        MainApp.carregarTela("menu.fxml", "Menu Principal");
    }
}