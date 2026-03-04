package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarUsuarioController {

    @FXML private ComboBox<String> comboTipoUsuario;
    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private TextField txtAnoNascimento;

    // Campos dinâmicos
    @FXML private Label lblCampo1;
    @FXML private TextField txtCampo1;
    @FXML private Label lblCampo2;
    @FXML private TextField txtCampo2;

    private Fachada fachada = Fachada.getInstance();

    @FXML
    public void initialize() {
        // Preenche as opções da caixa de seleção
        comboTipoUsuario.setItems(FXCollections.observableArrayList("Aluno", "Professor", "Funcionário"));

        // Fica "escutando" as mudanças na caixa de seleção para trocar os campos na tela
        comboTipoUsuario.getSelectionModel().selectedItemProperty().addListener((observable, valorAntigo, valorNovo) -> {
            atualizarCamposDinamicos(valorNovo);
        });
    }

    private void atualizarCamposDinamicos(String tipo) {
        if (tipo == null) return;

        // Limpa os campos sempre que trocar o tipo
        txtCampo1.clear();
        txtCampo2.clear();

        if (tipo.equals("Aluno")) {
            lblCampo1.setText("Matrícula:");
            lblCampo2.setText("Curso:");
            setCamposDinamicosVisiveis(true, true);
        } else if (tipo.equals("Professor")) {
            lblCampo1.setText("SIAPE:");
            setCamposDinamicosVisiveis(true, false);
        } else if (tipo.equals("Funcionário")) {
            lblCampo1.setText("Código de Acesso (8 dígitos):");
            setCamposDinamicosVisiveis(true, false);
        }
    }

    private void setCamposDinamicosVisiveis(boolean mostrarCampo1, boolean mostrarCampo2) {
        lblCampo1.setVisible(mostrarCampo1);
        txtCampo1.setVisible(mostrarCampo1);
        lblCampo2.setVisible(mostrarCampo2);
        txtCampo2.setVisible(mostrarCampo2);
    }

    @FXML
    private void salvarUsuario() {
        String tipo = comboTipoUsuario.getValue();
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String ano = txtAnoNascimento.getText();

        if (tipo == null || tipo.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione o tipo de usuário.");
            return;
        }

        try {
            // Repassa os dados para a fachada dependendo de quem está sendo cadastrado
            if (tipo.equals("Aluno")) {
                String matricula = txtCampo1.getText();
                String curso = txtCampo2.getText();
                fachada.cadastrarAluno(nome, cpf, ano, matricula, curso);

            } else if (tipo.equals("Professor")) {
                String siape = txtCampo1.getText();
                fachada.cadastrarProfessor(nome, cpf, ano, siape);

            } else if (tipo.equals("Funcionário")) {
                int codigoAcesso = Integer.parseInt(txtCampo1.getText());
                fachada.cadastrarFuncionario(nome, cpf, ano, codigoAcesso);
            }

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", tipo + " cadastrado com sucesso!");
            voltar(); // Volta para a tabela automaticamente após salvar

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Formato", "O código de acesso deve conter apenas números.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro no Cadastro", e.getMessage());
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
        MainApp.carregarTela("usuario.fxml", "Gerenciar Usuários");
    }
}
