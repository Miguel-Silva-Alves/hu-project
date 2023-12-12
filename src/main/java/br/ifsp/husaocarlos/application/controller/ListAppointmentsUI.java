package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.ifsp.husaocarlos.application.main.Main.findPatientUseCase;
import static br.ifsp.husaocarlos.application.main.Main.listAppointmentUseCase;

public class ListAppointmentsUI {

    // Label
    @FXML
    private Label lblSearch;

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
        loadDataAndShow();
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
        lblSearch.setText("Ação:");
        txtFilter.setText(action.getName());
        cAction.setText("Paciente");
        cAction.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        this.action = action;
        this.goBack = "HomeProfessor";
        loadDataAndShow();
    }

    private void loadDataAndShow(){
        List<Appointment> appointments;
        if(patient != null){
            appointments = listAppointmentUseCase.getAppointmentsOfPatient(patient);
        } else if (action != null) {
            appointments = listAppointmentUseCase.findAppointmentOfAction(action);
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
        txtFilter.setText(patient.getName());
        this.patient = patient;
        loadDataAndShow();
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
    public void findAppointments(MouseEvent event){

    }

}
