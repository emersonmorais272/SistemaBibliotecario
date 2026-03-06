package com.biblioteca.interfacebiblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApp extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        carregarTela("login.fxml", "Sistema Bibliotecário - Login");
    }

    public static void carregarTela(String fxml, String titulo) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                MainApp.class.getResource("/com/biblioteca/interfacebiblioteca/" + fxml)
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
    }
    public static void abrirPopup(String fxml, String titulo) {
        try {
            // Busca o arquivo na pasta de recursos
            URL url = MainApp.class.getResource("/com/biblioteca/interfacebiblioteca/" + fxml);

            if (url == null) {
                throw new RuntimeException("Arquivo FXML não encontrado: " + fxml);
            }

            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage novoStage = new Stage();
            novoStage.setTitle(titulo);
            novoStage.setScene(new Scene(root));

            novoStage.initModality(Modality.APPLICATION_MODAL);
            novoStage.show();

        } catch (IOException e) {
            System.err.println("Erro ao abrir a janela " + fxml);
            e.printStackTrace();
        }
    }
    // Cole isto dentro da classe MainApp, logo abaixo da declaração "private static Stage stage;"
    private static com.biblioteca.negocio.modelo.Usuario usuarioLogado;

    public static void setUsuarioLogado(com.biblioteca.negocio.modelo.Usuario u) {
        usuarioLogado = u;
    }

    public static com.biblioteca.negocio.modelo.Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}