package com.biblioteca.interfacebiblioteca;

import com.biblioteca.fachada.Fachada;
import com.biblioteca.negocio.modelo.Aluno;
import com.biblioteca.negocio.modelo.Funcionario;
import com.biblioteca.negocio.modelo.Professor;
import com.biblioteca.negocio.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AtualizarCadastroController {

    @FXML private TextField txtNome;
    @FXML private TextField txtAno;
    @FXML private TextField txtMatricula;
    @FXML private TextField txtCurso;
    public Button btnCabcelar;
    public Button btnAtualizar;

    Fachada fachada = Fachada.getInstance();

    public void initialize() {
        try {
            Usuario logado = MainApp.getUsuarioLogado();

            if (logado != null) {
                txtNome.setPromptText(logado.getNome());
                txtAno.setPromptText(String.valueOf(logado.getAnoNascimento()));

                if (logado instanceof Aluno) {
                    txtMatricula.setPromptText(((Aluno) logado).getMatricula());
                    txtCurso.setPromptText(((Aluno) logado).getCurso());
                }

                if(logado instanceof Professor)
                    txtMatricula.setPromptText(((Professor) logado).getSiape());

                if(logado instanceof Funcionario)
                    txtMatricula.setPromptText(Integer.toString(((Funcionario) logado).getCodigoAcesso()));
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados do usuário no prompt: " + e.getMessage());
        }
    }


    @FXML
    private void voltar() throws Exception {
        Usuario logado = MainApp.getUsuarioLogado();
        if(logado instanceof Funcionario) {
            MainApp.carregarTela("menuFuncionario.fxml", "Menu Principal");
        } else {
            MainApp.carregarTela("menuUsuarioComum.fxml", "Menu Principal");
        }
    }

    @FXML
    public void atualizar(ActionEvent event) {
        try {
            Usuario logado = MainApp.getUsuarioLogado();

            if (logado != null) {
                String nome = txtNome.getText().isEmpty() ? logado.getNome() : txtNome.getText();
                String ano = txtAno.getText().isEmpty() ? logado.getAnoNascimento() : txtAno.getText();
                String cpf = logado.getCPF();

                switch (logado) {
                    case Aluno aluno -> {
                        String matricula = txtMatricula.getText().isEmpty() ? aluno.getMatricula() : txtMatricula.getText();
                        String curso = txtCurso.getText().isEmpty() ? aluno.getCurso() : txtCurso.getText();

                        fachada.atualizarUsuario(cpf, nome, ano, matricula, curso);
                    }
                    case Professor professor -> {
                        String siape = txtMatricula.getText().isEmpty() ? professor.getSiape() : txtMatricula.getText();

                        fachada.atualizarUsuario(cpf, nome, ano, siape);
                    }
                    case Funcionario funcionario -> {
                        int codAcesso = txtMatricula.getText().isEmpty() ?
                                funcionario.getCodigoAcesso() : Integer.parseInt(txtMatricula.getText());

                        fachada.atualizarUsuario(cpf, nome, ano, codAcesso);
                    }
                    default -> {
                    }
                }
                mostrarAlerta("Sucesso", "Dados atualizados com sucesso!", Alert.AlertType.INFORMATION);
                voltar();
            }
        } catch (Exception e) {
            mostrarAlerta("Erro", "Ocorreu um problema: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

}
