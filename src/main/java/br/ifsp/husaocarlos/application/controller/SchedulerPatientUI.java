package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Schedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.ifsp.husaocarlos.application.main.Main.*;

public class SchedulerPatientUI {

    // TextField
    @FXML
    private TextField txtAction;

    // Label
    @FXML
    private Label lblNamePatient;

    // Table
    @FXML
    private TableView<Schedule> tableView;
    @FXML
    private TableColumn<Schedule, String> cAction;
    @FXML
    private TableColumn<Schedule, String> cDate;
    @FXML
    private TableColumn<Schedule, String> cStudent;

    private ObservableList<Schedule> tableData;

    Patient patient;

    @FXML
    private void initialize(){
        bindTableViewToItensList();
        bindColumnsToValueSources();
        //loadDataAndShow();
    }

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

    private void bindColumnsToValueSources() {
        cAction.setCellValueFactory(new PropertyValueFactory<>("actionName"));
        cDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        cStudent.setCellValueFactory(new PropertyValueFactory<>("studentName"));
    }

    private void bindTableViewToItensList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }


    public void populateTable(List<Schedule> list){
        tableData.clear();
        tableData.addAll(list);
    }



    @FXML
    public void findStudents(){
        String nameAction = txtAction.getText();
        Optional<Action> actionOptional = findActionUseCase.getActionByName(nameAction);
        if(actionOptional.isPresent()){
            List<Student> list = listStudentOfActionUseCase.listStudents(actionOptional.get());
            List<Schedule> schedules = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            for (Student student: list){
                String date = getNextHourFreeStudentUseCase.getNextHourFree(student).format(formatter);
                schedules.add(new Schedule(actionOptional.get().getName(), student.getName(), student.getCpf(), date));
            }

            populateTable(schedules);
        }else{
            System.out.println("SHOW DIALOG ERROR");
        }
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
