package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Acervo;
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
import java.util.stream.Collectors;

public class SolicitarEmprestimoController {

    @FXML private TableView<Acervo> tabelaAcervo;
    @FXML private TableColumn<Acervo, String> colTipo;
    @FXML private TableColumn<Acervo, String> colTitulo;
    @FXML private TableColumn<Acervo, String> colAutor;
    @FXML private TableColumn<Acervo, Number> colCodigo;

    private Fachada fachada = Fachada.getInstance();

    @FXML
    public void initialize() {
        configurarColunas();
        carregarAcervoDisponivel();
    }

    private void configurarColunas() {
        // Pega o nome da classe (Livro ou Artigo) para mostrar na tabela
        colTipo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));

        colTitulo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitulo()));

        colAutor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAutor()));

        colCodigo.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getCodigo()));
    }

    private void carregarAcervoDisponivel() {
        List<Acervo> todoAcervo = fachada.listarAcervo();

        if (todoAcervo != null) {
            // Filtra a lista para mostrar APENAS o que está disponível
            List<Acervo> disponiveis = todoAcervo.stream()
                    .filter(Acervo::isDisponivel)
                    .collect(Collectors.toList());

            ObservableList<Acervo> dados = FXCollections.observableArrayList(disponiveis);
            tabelaAcervo.setItems(dados);
        }
    }

    @FXML
    private void confirmarEmprestimo() {
        // Descobre qual item o usuário clicou na tabela
        Acervo itemSelecionado = tabelaAcervo.getSelectionModel().getSelectedItem();
        Usuario usuarioLogado = MainApp.getUsuarioLogado();

        if (itemSelecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione um item na tabela antes de confirmar.");
            return;
        }

        if (usuarioLogado == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Nenhum usuário logado no sistema.");
            return;
        }

        try {
            // Chama a lógica de negócio que você já tinha construído
            fachada.realizarEmprestimo(usuarioLogado, itemSelecionado);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso",
                    "Empréstimo realizado! Devolva em " + usuarioLogado.getPrazoEmprestimo() + " dias.");

            // Recarrega a tabela para o item sumir da lista de disponíveis
            carregarAcervoDisponivel();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro no Empréstimo", e.getMessage());
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
