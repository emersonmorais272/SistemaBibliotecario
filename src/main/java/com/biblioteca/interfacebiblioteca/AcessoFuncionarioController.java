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
            // Se acertou: Fecha essa telinha de senha
            Stage stage = (Stage) txtCodigoSecreto.getScene().getWindow();
            stage.close();

            // E abre a telinha exclusiva para cadastrar funcionário
            MainApp.abrirPopup("cadastrar_funcionario_login.fxml", "Cadastro de Funcionário");
        } else {
            // Se errou: Mostra o erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Acesso Negado");
            alert.setHeaderText(null);
            alert.setContentText("Código de acesso incorreto!");
            alert.showAndWait();
        }
    }

    @FXML
    private void voltar() {
        // Apenas fecha o popup e volta para o login que está no fundo
        Stage stage = (Stage) txtCodigoSecreto.getScene().getWindow();
        stage.close();
    }
}