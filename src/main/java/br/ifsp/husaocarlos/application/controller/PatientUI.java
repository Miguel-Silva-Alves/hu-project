package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.utils.exceptions.EntityAlreadyExistsException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static br.ifsp.husaocarlos.application.main.Main.*;

public class PatientUI {

    // BUTTON
    @FXML
    private Button btnSaveOrUpdate;

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

    private Patient patient;

    @FXML
    void saveOrUpdate(MouseEvent event) throws IOException {
        Patient patient = new Patient(txtCPF.getText(), txtName.getText(), txtEmail.getText(), txtPhone.getText(), txtAddress.getText());
        boolean newPatient = findPatientUseCase.findByCpf(patient.getCpf()).isEmpty();

        try{
            if (newPatient){
                boolean exec = createPatientUseCase.insert(patient);
                if(exec){
                    App.setRoot("SchedulerPatientUI");
                    SchedulerPatientUI controller = (SchedulerPatientUI) App.getController();
                    controller.setPatient(patient);
                }else{
                    Utils.showAlert("Erro", "Não foi possível criar o paciente!", Alert.AlertType.ERROR);
                }
            }else{
                boolean exec = updatePatientUseCase.update(patient);
                if(exec){
                    Utils.showAlert("Atualizado", "O paciente foi atualizado com sucesso!", Alert.AlertType.CONFIRMATION);
                }else{
                    Utils.showAlert("Erro", "Não foi possível atualizar o paciente!", Alert.AlertType.ERROR);
                }
            }

        }catch (EntityAlreadyExistsException e){
            Utils.showAlert("Erro", "CPF já cadastrado!", Alert.AlertType.ERROR);
        }catch (IllegalArgumentException e){
            //TODO Limpar os campos
            Utils.showAlert("Erro", "Verifique as informações passadas novamente!", Alert.AlertType.ERROR);
        }

    }

    private void setEntityToView(){
        txtName.setText(patient.getName());
        txtEmail.setText(patient.getEmail());
        txtPhone.setText(patient.getPhone());
        txtCPF.setText(patient.getCpf());
        txtAddress.setText(patient.getAddress());
    }

    public void setPatient(Patient patient) {
        if (patient == null){
            throw new IllegalArgumentException("User can not be null");
        }
        this.patient = patient;
        setEntityToView();
        btnSaveOrUpdate.setText("Atualizar");
        txtCPF.setDisable(true);
    }

    @FXML
    void toBack(MouseEvent event) throws IOException {
        App.setRoot("HomeReceptionist");
    }

}
