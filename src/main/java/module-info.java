module br.ifsp.husaocarlos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.security.enterprise.api;
    requires com.opencsv;
    requires jakarta.persistence;
    exports br.ifsp.husaocarlos.domain.entities;
    opens br.ifsp.husaocarlos to javafx.fxml;
    exports br.ifsp.husaocarlos;
    exports br.ifsp.husaocarlos.controller;
    opens br.ifsp.husaocarlos.controller to javafx.fxml;
    exports br.ifsp.husaocarlos.application.main;
    opens br.ifsp.husaocarlos.application.main to javafx.fxml;
    opens br.ifsp.husaocarlos.application.view to javafx.fxml;
    exports br.ifsp.husaocarlos.application;
    opens br.ifsp.husaocarlos.application to javafx.fxml;
}