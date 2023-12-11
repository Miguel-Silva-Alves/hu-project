package br.ifsp.husaocarlos.application.controller;

import javafx.scene.control.Alert;

public class Utils {

    public static void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
