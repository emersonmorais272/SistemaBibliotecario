package com.biblioteca.interfacebiblioteca;

import javafx.fxml.FXML;

public class MenuController {

    @FXML
    private void abrirUsuarios() throws Exception {
        MainApp.carregarTela("usuario.fxml", "Gerenciar Usuários");
    }

    @FXML
    private void abrirAcervo() throws Exception {
        MainApp.carregarTela("acervo.fxml", "Gerenciar Acervo");
    }

    @FXML
    private void abrirEmprestimos() throws Exception {
        MainApp.carregarTela("emprestimo.fxml", "Gerenciar Empréstimos");
    }

    @FXML
    private void sair() throws Exception {
        MainApp.carregarTela("login.fxml", "Login");
    }
}