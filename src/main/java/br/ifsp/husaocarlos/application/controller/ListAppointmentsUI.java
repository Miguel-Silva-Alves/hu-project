package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static br.ifsp.husaocarlos.application.main.Main.*;

public class ListAppointmentsUI {

    // Button
    @FXML
    private Button btnDischarge;

    // Label
    @FXML
    private Label lblSearch;
    @FXML
    private Label actionOrPatient;

    // TextField
    @FXML
    private TextField txtFilter;

    // Table
    @FXML
    private TableView<Appointment> tableView;
    @FXML
    private TableColumn<Appointment, String> cDate;
    @FXML
    private TableColumn<Appointment, String> cAction;
    @FXML
    private TableColumn<Appointment, String> cStudent;
    @FXML
    private TableColumn<Appointment, String> cStatus;

    private ObservableList<Appointment> tableData;

    private Patient patient;
    private Action action;

    private String goBack = "HomeReceptionist";

    @FXML
    private void initialize(){
        bindTableViewToItensList();
        bindColumnsToValueSources();
        loadDataAndShowActionFilter(null);
    }

    private void bindColumnsToValueSources() {
        cDate.setCellValueFactory(new PropertyValueFactory<>("dateFormated"));
        cAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        cStudent.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("statusName"));
    }

    private void bindTableViewToItensList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void setupActionModeView(Action action){
        lblSearch.setText("Paciente:");
        actionOrPatient.setText("Ação: " + action.getName());
        //txtFilter.setText(action.getName());
        cAction.setText("Paciente");
        cAction.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        this.action = action;
        this.goBack = "HomeProfessor";
    }

    private void loadDataAndShow(Patient patientFilter){
        List<Appointment> appointments;
        if (action != null) {
            appointments = listAppointmentUseCase.findAppointmentOfAction(action, patientFilter);
        } else{
            appointments = listAppointmentUseCase.findAll();
        }

        tableData.clear();
        if(appointments == null){
            Utils.showAlert("Sem dados", "Não encontramos nenhuma consulta!", Alert.AlertType.INFORMATION);
        }else{
            tableData.addAll(appointments);
        }
    }

    private void loadDataAndShowActionFilter(Action action){
        List<Appointment> appointments;
        if(patient != null){
            appointments = listAppointmentUseCase.getAppointmentsOfPatient(patient, action);
        } else{
            appointments = listAppointmentUseCase.findAll();
        }

        tableData.clear();
        if(appointments == null){
            Utils.showAlert("Sem dados", "Não encontramos nenhuma consulta!", Alert.AlertType.INFORMATION);
        }else{
            tableData.addAll(appointments);
        }
    }

    private void loadAppointmentsOfPatient(Patient patientFilter){
        List<Appointment> appointments;
        if (patientFilter != null) {
            appointments = listAppointmentUseCase.getAppointmentsOfPatient(patientFilter, null);
        } else{
            appointments = listAppointmentUseCase.findAll();
        }

        tableData.clear();
        if(appointments == null){
            Utils.showAlert("Sem dados", "Não encontramos nenhuma consulta!", Alert.AlertType.INFORMATION);
        }else{
            tableData.addAll(appointments);
        }
    }

    public void setPatient(Patient patient) {
        if (patient == null){
            throw new IllegalArgumentException("patient can not be null");
        }
        actionOrPatient.setText("Patient: " + patient.getName());
        btnDischarge.setVisible(false);
        this.patient = patient;
        loadAppointmentsOfPatient(patient);
    }

    public void setAction(Action action) {
        if (action == null){
            throw new IllegalArgumentException("action can not be null");
        }
        setupActionModeView(action);
    }

    @FXML
    void toBack(MouseEvent event) throws IOException {
        App.setRoot(goBack);
    }

    @FXML
    void findAppointments(MouseEvent event){
        String whatSearch = lblSearch.getText();

        try{
            if(whatSearch.equals("Paciente:")){
                // Filtering action appointments by patient
                Optional<Patient> patientOptional = findPatientUseCase.findByCpf(txtFilter.getText());
                if(patientOptional.isPresent()){
                    loadDataAndShow(patientOptional.get());
                }else{
                    Utils.showAlert("Paciente não encontrado", "Não foi possível encontrar o paciente!\nDigite novamente o CPF.", Alert.AlertType.ERROR);
                }
            }else{
                // Filtering patient appointments by action
                Optional<Action> act = findActionUseCase.findActionByName(txtFilter.getText());
                if(act.isPresent()){
                    loadDataAndShowActionFilter(act.get());
                }else{
                    Utils.showAlert("Ação não encontrada", "Não foi possível encontrar a ação!", Alert.AlertType.ERROR);
                }
            }
        }catch (IllegalArgumentException e){
            Utils.showAlert("Erro", "Erro ao procurar: " + e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    void toDischarge(MouseEvent event){
        Appointment appointment = tableView.getSelectionModel().getSelectedItem();
        if(appointment != null){
            boolean wasDeleted = dischargePatient.discharge(appointment.getPatient(),appointment);
            if(wasDeleted){
                Utils.showAlert("Sucesso", "O paciente recebeu alta!", Alert.AlertType.CONFIRMATION);
            }else{
                Utils.showAlert("Erro", "O paciente não pode receber alta nesse momento!", Alert.AlertType.ERROR);
            }
        }else{
            Utils.showAlert("Consulta não encontrada", "É necessário escolher uma consulta!", Alert.AlertType.ERROR);
        }
        loadDataAndShow(patient);
    }

}
