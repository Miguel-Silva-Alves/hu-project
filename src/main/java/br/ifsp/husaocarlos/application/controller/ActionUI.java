package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.usecases.utils.exceptions.EntityAlreadyExistsException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static br.ifsp.husaocarlos.application.main.Main.*;

public class ActionUI {

    // TEXTFIELDS
    @FXML
    private TextField txtSpecialty;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLineOfCare;
    private Professor professor;

    @FXML
    void save(MouseEvent event) throws IOException {

        Action action = new Action(txtName.getText(), txtSpecialty.getText(), professor, findLineOfCareUseCase.findLineOfCareByName(txtLineOfCare.getText()).get());
        try{
            if(registerActionUseCase.registerAction(action)){
                Utils.showAlert("Sucesso", "A ação foi registrada com sucesso!", Alert.AlertType.CONFIRMATION);
            }else{
                Utils.showAlert("Erro", "Não foi possível criar a ação!", Alert.AlertType.ERROR);
            }
        }catch (IllegalArgumentException e){
            Utils.showAlert("Erro", "Não foi possível criar a ação!", Alert.AlertType.ERROR);
        }
        txtName.setText("");
        txtSpecialty.setText("");
        txtLineOfCare.setText("");
    }

//    private void setEntityToView(){
//        txtName.setText(patient.getName());
//        txtEmail.setText(patient.getEmail());
//        txtPhone.setText(patient.getPhone());
//        txtCPF.setText(patient.getCpf());
//        txtAddress.setText(patient.getAddress());
//    }

    public void setProfessor(Professor professor) {
        if (professor == null){
            throw new IllegalArgumentException("Professor can not be null");
        }
        this.professor = professor;
        //setEntityToView();
        //btnSaveOrUpdate.setText("Atualizar");
        //txtCPF.setDisable(true);
    }

    @FXML
    void toBack(MouseEvent event) throws IOException {
        App.setRoot("HomeProfessor");
    }
}
