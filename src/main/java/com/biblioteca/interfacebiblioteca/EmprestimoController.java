package com.biblioteca.interfacebiblioteca;

import javafx.fxml.FXML;

public class EmprestimoController {

    @FXML
    private void voltar() throws Exception {
        MainApp.carregarTela("menu.fxml", "Menu Principal");
    }
    @FXML
    private void abrirMeusEmprestimos() throws Exception {
        MainApp.carregarTela("meus_emprestimos.fxml", "Meus Empréstimos Ativos");
    }
    @FXML
    private void abrirSolicitarEmprestimo() throws Exception {
        MainApp.carregarTela("solicitar_emprestimo.fxml", "Solicitar Novo Empréstimo");
    }
    @FXML
    private void abrirDevolverItem() throws Exception {
        MainApp.carregarTela("devolver_item.fxml", "Devolver Item");
    }
}