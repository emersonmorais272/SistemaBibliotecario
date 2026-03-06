package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MenuController {

    Fachada fachada = Fachada.getInstance();

    @FXML
    private void abrirUsuarios() throws Exception {
        MainApp.carregarTela("usuario.fxml", "Gerenciar Usuários");
    }

    @FXML
    private void abrirAcervo() throws Exception {
        MainApp.carregarTela("acervo.fxml", "Gerenciar Acervo");
    }

    @FXML
    private void sair() throws Exception {
        MainApp.carregarTela("login.fxml", "Login");
    }

    public void abrirAtualizar(ActionEvent actionEvent) throws Exception {
        MainApp.carregarTela("atualizarCadastroProfessorFuncionario.fxml", "tela de atualizacao");
    }

    public void removerConta(ActionEvent actionEvent) throws Exception {
        if (confirmarAcao("APAGAR CONTA", "Tem certeza que quer prosseguir? Esta ação é permanente.")) {
            fachada.removerUsuario(MainApp.getUsuarioLogado().getCPF());
            MainApp.setUsuarioLogado(null);
            MainApp.carregarTela("login.fxml", "Tela de Login");
        }
    }

    private boolean confirmarAcao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(botaoSim, botaoNao);

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == botaoSim;
    }
}