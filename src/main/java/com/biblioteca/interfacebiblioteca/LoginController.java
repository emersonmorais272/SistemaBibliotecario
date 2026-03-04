package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class LoginController {

    @FXML private TextField txtUsuario; // vamo usar para o CPF

    private Fachada fachada = Fachada.getInstance();

    @FXML
    private void entrar() throws Exception {
        String cpf = txtUsuario.getText();

        Usuario user = fachada.buscarUsuario(cpf);

        if (user != null) {
            MainApp.setUsuarioLogado(user);
            System.out.println("Login com sucesso: " + user.getNome());
            MainApp.carregarTela("menu.fxml", "Menu Principal - " + user.getNome());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Acesso");
            alert.setHeaderText(null);
            alert.setContentText("CPF não encontrado no sistema!");
            alert.showAndWait();
        }
    }
}