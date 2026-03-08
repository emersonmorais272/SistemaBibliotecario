package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Artigo;
import com.biblioteca.negocio.modelo.Livro;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarAcervoController {

    @FXML private ComboBox<String> comboTipo;
    @FXML private TextField txtTitulo, txtAutor, txtCodigo, txtQtd;


    @FXML private Label lblIsbn;
    @FXML private TextField txtIsbn;


    @FXML private Label lblMes, lblEdicao, lblLocal;
    @FXML private TextField txtMes, txtEdicao, txtLocal;

    private Fachada fachada = Fachada.getInstance();

    @FXML
    public void initialize() {
        comboTipo.setItems(FXCollections.observableArrayList("Livro", "Artigo"));


        comboTipo.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            atualizarCampos(novo);
        });
    }

    private void atualizarCampos(String tipo) {
        boolean isLivro = "Livro".equals(tipo);
        boolean isArtigo = "Artigo".equals(tipo);

        lblIsbn.setVisible(isLivro);
        txtIsbn.setVisible(isLivro);

        lblMes.setVisible(isArtigo);
        txtMes.setVisible(isArtigo);
        lblEdicao.setVisible(isArtigo);
        txtEdicao.setVisible(isArtigo);
        lblLocal.setVisible(isArtigo);
        txtLocal.setVisible(isArtigo);
    }

    @FXML
    private void salvarItem() {
        try {
            String tipo = comboTipo.getValue();
            if (tipo == null) {
                mostrarAlerta("Atenção", "Selecione o tipo de item.", Alert.AlertType.WARNING);
                return;
            }

            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int codigo = Integer.parseInt(txtCodigo.getText());
            int qtd = Integer.parseInt(txtQtd.getText());

            if (tipo.equals("Livro")) {
                String isbn = txtIsbn.getText();
                Livro livro = new Livro(titulo, autor, codigo, true, qtd, isbn);
                fachada.cadastrarItemAcervo(livro);
            } else {
                String mes = txtMes.getText();
                int edicao = Integer.parseInt(txtEdicao.getText());
                String local = txtLocal.getText();
                Artigo artigo = new Artigo(titulo, autor, codigo, true, qtd, mes, edicao, local);
                fachada.cadastrarItemAcervo(artigo);
            }

            mostrarAlerta("Sucesso", tipo + " cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            voltar();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro de Formato", "Código, Quantidade e Edição devem ser números.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Erro", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void voltar() throws Exception {
        MainApp.carregarTela("acervo.fxml", "Gerenciar Acervo");
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
