package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.domain.entities.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SchedulerPatientUI {

    // Buttons
    @FXML
    private Button btnFindAction;
    @FXML
    private Button btnSchedule;

    // Label
    @FXML
    private Label lblNamePatient;


    Patient patient;

    private void setEntityToView(){
        lblNamePatient.setText(patient.getName());
    }


    public void setPatient(Patient patient) {
        if (patient == null){
            throw new IllegalArgumentException("User can not be null");
        }
        this.patient = patient;
        setEntityToView();
    }

    /*
    *
    *
    * - Pegar o nome do textfield
    - Achar a ação com esse nome
    - Procurar os estudantes dessa ação
    - Popular a próxima hora livre deles
    *
    * */
}
