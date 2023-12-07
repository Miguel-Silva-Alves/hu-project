package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Receptionist;
import br.ifsp.husaocarlos.domain.entities.User;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HomeReceptionist {

    public HomeReceptionist() {
        UserHolder userHolder = UserHolder.getInstance();
        User u = userHolder.getUser();
        Receptionist receptionist = new Receptionist(u);
        System.out.println(receptionist);

    }

    @FXML
    void changeRegisterPatientView(MouseEvent event) throws IOException {
        App.setRoot("RegisterPatientUI");
    }


}
