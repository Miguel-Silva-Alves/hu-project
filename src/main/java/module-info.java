module br.ifsp.husaocarlos {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.ifsp.husaocarlos to javafx.fxml;
    exports br.ifsp.husaocarlos;
    exports br.ifsp.husaocarlos.controller;
    opens br.ifsp.husaocarlos.controller to javafx.fxml;
}