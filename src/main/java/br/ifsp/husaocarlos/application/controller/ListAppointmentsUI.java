package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.ifsp.husaocarlos.application.main.Main.findPatientUseCase;
import static br.ifsp.husaocarlos.application.main.Main.listAppointmentUseCase;

public class ListAppointmentsUI {

    // TextField
    @FXML
    private TextField txtPatient;

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

    private void loadDataAndShow(){
        List<Appointment> appointments;
        if(patient == null){
            appointments = listAppointmentUseCase.findAll();
        }else{
            appointments = listAppointmentUseCase.getAppointmentsOfPatient(patient);
        }

        tableData.clear();
        if(appointments == null){
            System.out.println("Appointments is null!!!");
        }else{
            tableData.addAll(appointments);
        }
    }

    public void setPatient(Patient patient) {
        if (patient == null){
            throw new IllegalArgumentException("patient can not be null");
        }
        txtPatient.setText(patient.getName());
        this.patient = patient;
        loadDataAndShow();
    }

    @FXML
    void toBack(MouseEvent event) throws IOException {
        App.setRoot("HomeReceptionist");
    }

    @FXML
    public void findAppointments(MouseEvent event){

    }

}
