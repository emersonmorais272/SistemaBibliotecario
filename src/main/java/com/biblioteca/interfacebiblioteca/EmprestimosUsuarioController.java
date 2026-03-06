package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Emprestimo;
import com.biblioteca.negocio.modelo.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;

public class EmprestimosUsuarioController {

    @FXML private Label lblTitulo;
    @FXML private TableView<Emprestimo> tabelaEmprestimos;
    @FXML private TableColumn<Emprestimo, String> colTitulo;
    @FXML private TableColumn<Emprestimo, String> colDataEmp;
    @FXML private TableColumn<Emprestimo, String> colDataDev;
    @FXML private TableColumn<Emprestimo, String> colMulta;

    private Fachada fachada = Fachada.getInstance();
    private DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {
        Usuario inspecionado = MainApp.getUsuarioInspecionado();
        if (inspecionado != null) {
            lblTitulo.setText("Empréstimos de: " + inspecionado.getNome());
        }

        configurarColunas();
        carregarDados();
    }

    private void configurarColunas() {
        colTitulo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getItem().getTitulo()));

        colDataEmp.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataEmprestimo().format(formatador)));

        colDataDev.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataPrevistaDevolucao().format(formatador)));

        colMulta.setCellValueFactory(cellData -> {
            Emprestimo e = cellData.getValue();
            LocalDate hoje = LocalDate.now();
            double multaAtual = 0.0;

            if (hoje.isAfter(e.getDataPrevistaDevolucao()) && !e.isMultaPerdoada()) {
                long diasAtraso = ChronoUnit.DAYS.between(e.getDataPrevistaDevolucao(), hoje);
                multaAtual = e.getUsuario().calcularMulta(diasAtraso);
            }

            return new SimpleStringProperty(String.format("R$ %.2f", multaAtual));
        });
    }

    private void carregarDados() {
        Usuario inspecionado = MainApp.getUsuarioInspecionado();
        if (inspecionado == null) return;

        List<Emprestimo> todos = fachada.listarEmprestimos();

        if (todos != null) {
            // Filtra os empréstimos para mostrar só os do usuário que estamos inspecionando
            List<Emprestimo> doUsuario = todos.stream()
                    .filter(e -> e.getUsuario().getCPF().equals(inspecionado.getCPF()))
                    .collect(Collectors.toList());

            ObservableList<Emprestimo> dados = FXCollections.observableArrayList(doUsuario);
            tabelaEmprestimos.setItems(dados);
        }
    }

    @FXML
    private void voltar() throws Exception {
        // Limpa a memória e volta para a tela de gerenciamento
        MainApp.setUsuarioInspecionado(null);
        MainApp.carregarTela("usuario.fxml", "Gerenciar Usuários");
    }

    @FXML
    private void perdoarMulta() {
        Emprestimo selecionado = tabelaEmprestimos.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um empréstimo na tabela primeiro.");
            alert.showAndWait();
            return;
        }

        if (selecionado.isMultaPerdoada()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "A multa deste item já foi perdoada!");
            alert.showAndWait();
            return;
        }

        fachada.perdoarMultaEmprestimo(selecionado);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Multa perdoada com sucesso!");
        alert.showAndWait();

        tabelaEmprestimos.refresh(); // Atualiza a tabela instantaneamente (vai zerar o valor)
    }
}