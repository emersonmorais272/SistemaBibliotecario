module com.biblioteca.interfacebiblioteca {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.biblioteca.interfacebiblioteca to javafx.fxml;
    exports com.biblioteca.interfacebiblioteca;
}