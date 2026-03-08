package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarFuncionarioLoginController {

    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private TextField txtAnoNascimento;
    @FXML private TextField txtCodigoAcesso;

    private Fachada fachada = Fachada.getInstance();

    @FXML
    private void salvarFuncionario() {
        try {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String ano = txtAnoNascimento.getText();
            int cod = Integer.parseInt(txtCodigoAcesso.getText());


            fachada.cadastrarFuncionario(nome, cpf, ano, cod);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Funcionário cadastrado com sucesso!");
            alert.showAndWait();


            Stage stage = (Stage) txtNome.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "O Código de acesso deve conter apenas números!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void voltar() {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }
}