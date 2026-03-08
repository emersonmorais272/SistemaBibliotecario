package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Aluno;
import com.biblioteca.negocio.modelo.Professor;
import com.biblioteca.negocio.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginController {

    public Hyperlink linkCadastrar;
    @FXML private TextField txtUsuario;

    private Fachada fachada = Fachada.getInstance();

    @FXML
    private void entrar() throws Exception {
        String cpf = txtUsuario.getText();

        Usuario user = fachada.buscarUsuario(cpf);

        if (user != null) {
            if(user instanceof Professor || user instanceof Aluno){
                MainApp.setUsuarioLogado(user);
                System.out.println("Login com sucesso: " + user.getNome());
                MainApp.carregarTela("menuUsuarioComum.fxml", "Menu Principal - " + user.getNome());

            } else {
                MainApp.setUsuarioLogado(user);
                System.out.println("Login com sucesso: " + user.getNome());
                MainApp.carregarTela("menuFuncionario.fxml", "Menu Principal - " + user.getNome());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Acesso");
            alert.setHeaderText(null);
            alert.setContentText("CPF não encontrado no sistema!");
            alert.showAndWait();
        }
    }

    @FXML
    public void abrirTelaCadastro(javafx.event.ActionEvent actionEvent) throws Exception {
        MainApp.abrirPopup("cadastrarLogin.fxml", "cadastro novo usuario");
    }
    @FXML
    public void abrirTelaAcessoFuncionario(javafx.event.ActionEvent actionEvent) {

        MainApp.abrirPopup("acesso_funcionario.fxml", "Acesso Restrito - Funcionário");
    }

}