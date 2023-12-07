package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.application.repository.InMemoryPatientDAO;
import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.patient.CreatePatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.utils.exceptions.EntityAlreadyExistsException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static br.ifsp.husaocarlos.application.main.Main.createPatientUseCase;

public class RegisterPatientUI {

    // TEXTFIELDS
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAddress;

    @FXML
    void registerUser(MouseEvent event) throws IOException {
        Patient patient = new Patient(txtCPF.getText(), txtName.getText(), txtEmail.getText(), txtPhone.getText(), txtAddress.getText());
        try{
            boolean exec = createPatientUseCase.insert(patient);
            if(exec){
                App.setRoot("SchedulerPatientUI");
                SchedulerPatientUI controller = (SchedulerPatientUI) App.getController();
                controller.setPatient(patient);
            }
            System.out.println(exec);
        }catch (EntityAlreadyExistsException e){
            System.out.println(e);
        }

    }

}
