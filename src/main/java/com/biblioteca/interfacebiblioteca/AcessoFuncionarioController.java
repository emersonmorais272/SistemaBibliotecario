package com.biblioteca.interfacebiblioteca;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class AcessoFuncionarioController {

    @FXML private PasswordField txtCodigoSecreto;

    @FXML
    private void verificarCodigo() {
        String digitado = txtCodigoSecreto.getText();

        if (digitado.equals("2112")) {

            Stage stage = (Stage) txtCodigoSecreto.getScene().getWindow();
            stage.close();


            MainApp.abrirPopup("cadastrar_funcionario_login.fxml", "Cadastro de Funcionário");
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Acesso Negado");
            alert.setHeaderText(null);
            alert.setContentText("Código de acesso incorreto!");
            alert.showAndWait();
        }
    }

    @FXML
    private void voltar() {

        Stage stage = (Stage) txtCodigoSecreto.getScene().getWindow();
        stage.close();
    }
}