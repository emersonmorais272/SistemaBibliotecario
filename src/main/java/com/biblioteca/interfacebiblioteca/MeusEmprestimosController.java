package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Emprestimo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class MeusEmprestimosController {

    @FXML private TableView<Emprestimo> tabelaEmprestimos;
    @FXML private TableColumn<Emprestimo, String> colTitulo;
    @FXML private TableColumn<Emprestimo, String> colDataEmp;
    @FXML private TableColumn<Emprestimo, String> colDataDev;
    @FXML private TableColumn<Emprestimo, String> colMulta;


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

        colDataEmp.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataEmprestimo().format(formatador)));

        colDataDev.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataPrevistaDevolucao().format(formatador)));

        // A MÁGICA DO PERDÃO ACONTECE AQUI
        colMulta.setCellValueFactory(cellData -> {
            Emprestimo e = cellData.getValue();
            java.time.LocalDate hoje = java.time.LocalDate.now();
            double multaAtual = 0.0;

            // Só calcula a multa se estiver atrasado E a multa NÃO tiver sido perdoada!
            if (hoje.isAfter(e.getDataPrevistaDevolucao()) && !e.isMultaPerdoada()) {
                long diasAtraso = java.time.temporal.ChronoUnit.DAYS.between(e.getDataPrevistaDevolucao(), hoje);
                multaAtual = e.getUsuario().calcularMulta(diasAtraso);
            }

            return new SimpleStringProperty(String.format("R$ %.2f", multaAtual));
        });
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
    private void voltar() throws Exception {
        MainApp.carregarTela("emprestimo.fxml", "Gerenciar Empréstimos");
    }
}
