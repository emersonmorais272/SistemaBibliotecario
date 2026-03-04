package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Emprestimo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DevolverItemController {

    @FXML private TableView<Emprestimo> tabelaEmprestimos;
    @FXML private TableColumn<Emprestimo, String> colTitulo;
    @FXML private TableColumn<Emprestimo, String> colDataDev;

    private Fachada fachada = Fachada.getInstance();
    private DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {
        configurarColunas();
        carregarDados();
    }

    private void configurarColunas() {
        colTitulo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getItem().getTitulo()));

        colDataDev.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataPrevistaDevolucao().format(formatador)));
    }

    private void carregarDados() {
        if (MainApp.getUsuarioLogado() == null) return;

        String cpfLogado = MainApp.getUsuarioLogado().getCPF();
        List<Emprestimo> todos = fachada.listarEmprestimos();

        if (todos != null) {
            List<Emprestimo> meus = todos.stream()
                    .filter(e -> e.getUsuario().getCPF().equals(cpfLogado))
                    .collect(Collectors.toList());

            ObservableList<Emprestimo> dados = FXCollections.observableArrayList(meus);
            tabelaEmprestimos.setItems(dados);
        }
    }

    @FXML
    private void confirmarDevolucao() {
        Emprestimo selecionado = tabelaEmprestimos.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione um item na tabela para devolver.");
            return;
        }

        try {
            String cpfUsuario = MainApp.getUsuarioLogado().getCPF();

            // Chama a sua regra de negócio que calcula a multa
            double multa = fachada.finalizarDevolucao(cpfUsuario);

            if (multa > 0) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Devolução com Atraso",
                        String.format("Item devolvido com sucesso!\nMulta gerada: R$ %.2f", multa));
            } else {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Devolução no Prazo",
                        "Item devolvido com sucesso! Nenhuma multa foi gerada.");
            }

            // Atualiza a tabela para remover o item devolvido
            carregarDados();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", e.getMessage());
        }
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
        MainApp.carregarTela("emprestimo.fxml", "Gerenciar Empréstimos");
    }
}
