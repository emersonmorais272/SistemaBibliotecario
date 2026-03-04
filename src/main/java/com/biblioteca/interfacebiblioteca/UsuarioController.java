package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Usuario;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class UsuarioController {

    @FXML private TableView<Usuario> tabelaUsuarios;
    @FXML private TableColumn<Usuario, String> colTipo;
    @FXML private TableColumn<Usuario, String> colNome;
    @FXML private TableColumn<Usuario, String> colCpf;
    @FXML private TableColumn<Usuario, Number> colIdade;

    private Fachada fachada = Fachada.getInstance();

    @FXML
    public void initialize() {
        configurarColunas();
        carregarUsuarios(); // CHAMA A LISTAGEM AUTOMATICA AQUI
    }

    private void configurarColunas() {
        colTipo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));

        colNome.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNome()));

        colCpf.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCPF()));

        colIdade.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getIdade()));
    }

    // AQUI ESTÁ A LÓGICA DE LISTAR
    private void carregarUsuarios() {
        List<Usuario> usuarios = fachada.listarUsuario();
        if (usuarios != null) {
            ObservableList<Usuario> dados = FXCollections.observableArrayList(usuarios);
            tabelaUsuarios.setItems(dados);
        }
    }

    // AQUI ESTÁ A LÓGICA DE REMOVER
    @FXML
    private void removerUsuarioSelecionado() {
        Usuario selecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione um usuário na tabela para remover.");
            return;
        }

        try {
            fachada.removerUsuario(selecionado.getCPF());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Usuário removido do sistema.");
            carregarUsuarios(); // Atualiza a tabela na mesma hora
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", e.getMessage());
        }
    }

    @FXML
    private void abrirCadastro() throws Exception {
        MainApp.carregarTela("cadastrar_usuario.fxml", "Cadastrar Novo Usuário");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void voltar() throws Exception {
        MainApp.carregarTela("menu.fxml", "Menu Principal");
    }
}