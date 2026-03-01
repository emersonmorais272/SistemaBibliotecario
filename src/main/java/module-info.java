module com.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    // isso aqui abre o pacote de interface para o FX encontrar seus Controllers
    opens com.biblioteca.interfacebiblioteca to javafx.fxml;

    // abre os modelos para as TableViews conseguirem ler os dados dos objetos
    opens com.biblioteca.negocio.modelo to javafx.base;

    exports com.biblioteca.interfacebiblioteca;
}