package com.biblioteca.interfacebiblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
}