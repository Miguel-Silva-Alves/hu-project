package br.ifsp.husaocarlos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HUSystemController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}