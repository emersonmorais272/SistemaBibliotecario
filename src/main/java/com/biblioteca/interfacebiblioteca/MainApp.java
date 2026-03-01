package com.biblioteca.interfacebiblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/biblioteca/interfacebiblioteca/login.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sistema Bibliotecário - Login");
        stage.setScene(scene);
        stage.show();
    }
}