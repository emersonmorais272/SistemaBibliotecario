package com.biblioteca.interfacebiblioteca;

import com.biblioteca.negocio.modelo.Funcionario;
import com.biblioteca.negocio.modelo.Usuario;
import javafx.fxml.FXML;

public class EmprestimoController {

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
    private void abrirMeusEmprestimos() throws Exception {
        MainApp.carregarTela("meus_emprestimos.fxml", "Meus Empréstimos Ativos");
    }
    @FXML
    private void abrirSolicitarEmprestimo() throws Exception {
        MainApp.carregarTela("solicitar_emprestimo.fxml", "Solicitar Novo Empréstimo");
    }
    @FXML
    private void abrirDevolverItem() throws Exception {
        MainApp.carregarTela("devolver_item.fxml", "Devolver Item");
    }
}