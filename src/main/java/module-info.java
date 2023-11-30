module br.ifsp.husaocarlos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.security.enterprise.api;
    requires com.opencsv;
    requires jakarta.persistence;
    opens br.ifsp.husaocarlos.application.main to javafx.fxml;
    opens br.ifsp.husaocarlos.application.controller to javafx.fxml;
    exports br.ifsp.husaocarlos.application.view;
    exports br.ifsp.husaocarlos.domain.entities;
    opens br.ifsp.husaocarlos.application.view to javafx.fxml;
}