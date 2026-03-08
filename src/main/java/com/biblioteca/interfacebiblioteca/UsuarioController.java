package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Emprestimo;
import com.biblioteca.negocio.modelo.Usuario;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UsuarioController {

    @FXML private TableView<Usuario> tabelaUsuarios;
    @FXML private TableColumn<Usuario, String> colTipo;
    @FXML private TableColumn<Usuario, String> colNome;
    @FXML private TableColumn<Usuario, String> colCpf;
    @FXML private TableColumn<Usuario, Number> colIdade;
    @FXML private TableColumn<Usuario, String> colMulta;

    private Fachada fachada = Fachada.getInstance();

    @FXML
    public void initialize() {
        configurarColunas();
        carregarUsuarios();
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


        colMulta.setCellValueFactory(cellData -> {
            Usuario u = cellData.getValue();
            List<Emprestimo> todosEmprestimos = fachada.listarEmprestimos();
            double multaTotal = 0.0;

            if (todosEmprestimos != null) {
                for (Emprestimo e : todosEmprestimos) {
                    if (e.getUsuario().getCPF().equals(u.getCPF())) {
                        LocalDate hoje = LocalDate.now();
                        if (hoje.isAfter(e.getDataPrevistaDevolucao())) {
                            long diasAtraso = ChronoUnit.DAYS.between(e.getDataPrevistaDevolucao(), hoje);
                            multaTotal += u.calcularMulta(diasAtraso);
                        }
                    }
                }
            }
            return new SimpleStringProperty(String.format("R$ %.2f", multaTotal));
        });
    }

    private void carregarUsuarios() {
        List<Usuario> usuarios = fachada.listarUsuario();
        if (usuarios != null) {
            ObservableList<Usuario> dados = FXCollections.observableArrayList(usuarios);
            tabelaUsuarios.setItems(dados);
        }
    }

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
            carregarUsuarios();
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
        MainApp.carregarTela("menuFuncionario.fxml", "Menu Principal");
    }

    @FXML
    private void verEmprestimos() throws Exception {
        Usuario selecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione um usuário na tabela para ver seus empréstimos.");
            return;
        }


        MainApp.setUsuarioInspecionado(selecionado);
        MainApp.carregarTela("emprestimos_usuario.fxml", "Empréstimos de " + selecionado.getNome());
    }
}