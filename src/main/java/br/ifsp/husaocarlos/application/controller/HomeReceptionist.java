package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Receptionist;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.utils.CheckCPF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.ifsp.husaocarlos.application.main.Main.findPatientUseCase;

public class HomeReceptionist {

    // TextField
    @FXML
    private TextField txtPacient;

    // Table
    @FXML
    private TableView<Patient> tableView;
    @FXML
    private TableColumn<Patient, String> cName;
    @FXML
    private TableColumn<Patient, String> cPhone;
    @FXML
    private TableColumn<Patient, String> cAddress;

    private ObservableList<Patient> tableData;

    public HomeReceptionist() {
        UserHolder userHolder = UserHolder.getInstance();
        User u = userHolder.getUser();
        Receptionist receptionist = new Receptionist(u);
        System.out.println(receptionist);

    }

    @FXML
    private void initialize(){
        bindTableViewToItensList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindColumnsToValueSources() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void bindTableViewToItensList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void loadDataAndShow(){
        List<Patient> patients = findPatientUseCase.findAll();
        tableData.clear();
        tableData.addAll(patients);
    }

    @FXML
    void changeRegisterPatientView(MouseEvent event) throws IOException {
        App.setRoot("PatientUI");
    }

    @FXML
    void searchPatient(MouseEvent event){
        String texto = txtPacient.getText();
        if(CheckCPF.checkCpf(texto)){
            // Is a cpf
            Optional<Patient> patientOptional = findPatientUseCase.findByCpf(texto);
            if(patientOptional.isPresent()){
                List<Patient> patt = new ArrayList<>();
                patt.add(patientOptional.get());
                tableData.clear();
                tableData.addAll(patt);
            }else{
                Utils.showAlert("Não encontrado", "Não foi possível encontrar um paciente com esse cpf!", Alert.AlertType.INFORMATION);
            }
        }else{
            // Is a name
            List<Patient> patt = findPatientUseCase.findByName(texto);
            tableData.clear();
            tableData.addAll(patt);
        }
    }

    @FXML
    void toSchedule(MouseEvent event) throws IOException {
        Patient patient = tableView.getSelectionModel().getSelectedItem();
        if(patient != null){
            App.setRoot("SchedulerPatientUI");
            SchedulerPatientUI controller = (SchedulerPatientUI) App.getController();
            controller.setPatient(patient);
        }else{
            Utils.showAlert("Paciente não encontrado", "É necessário escolher um paciente!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void toSearchAppointments(MouseEvent event) throws IOException {
        Patient patient = tableView.getSelectionModel().getSelectedItem();
        if(patient != null){
            App.setRoot("ListAppointmentsUI");
            ListAppointmentsUI controller = (ListAppointmentsUI) App.getController();
            controller.setPatient(patient);
        }else{
            Utils.showAlert("Paciente não encontrado", "É necessário escolher um paciente!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void toEditPatient(MouseEvent event) throws IOException {
        Patient patient = tableView.getSelectionModel().getSelectedItem();
        if(patient != null) {
            App.setRoot("PatientUI");
            PatientUI controller = (PatientUI) App.getController();
            controller.setPatient(patient);
        }else{
            Utils.showAlert("Paciente não encontrado", "É necessário escolher um paciente!", Alert.AlertType.ERROR);
        }
    }

}
