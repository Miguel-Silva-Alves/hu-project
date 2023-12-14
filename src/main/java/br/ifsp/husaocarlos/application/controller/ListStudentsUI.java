package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.appointment.Schedule;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.ifsp.husaocarlos.application.main.Main.findStudentsUseCase;
import static br.ifsp.husaocarlos.application.main.Main.registerStudentActionUseCase;

public class ListStudentsUI {

    // Button
    @FXML
    private Button btnSchedule;

    // Label
    @FXML
    private Label lblAction;

    // TextField
    @FXML
    private TextField txtStudent;

    // Table
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, String> cName;
    @FXML
    private TableColumn<Student, String> cRegistration;
    @FXML
    private TableColumn<Student, String> cEmail;
    private ObservableList<Student> tableData;

    private Action action;
    private boolean getAll = false;

    @FXML
    private void initialize(){
        bindTableViewToItensList();
        bindColumnsToValueSources();
        //loadDataAndShow();
    }

    private void bindColumnsToValueSources() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cRegistration.setCellValueFactory(new PropertyValueFactory<>("registration"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void bindTableViewToItensList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void loadStudentsOfAction(Action action){
        if(action != null){
            List<Student> students = findStudentsUseCase.findStudentsOfAction(action);
            tableData.clear();
            tableData.addAll(students);
        }
    }

    private void loadAllStudents(){
        if(action != null){
            List<Student> students = findStudentsUseCase.findAll();
            tableData.clear();
            tableData.addAll(students);
        }
        getAll = true;
    }

    public void setActionToAddStudent(Action action) {
        if (action == null){
            throw new IllegalArgumentException("action can not be null");
        }
        this.action = action;
        lblAction.setText(action.getName());
        loadAllStudents();
    }

    public void setActionToSeeStudents(Action action) {
        if (action == null){
            throw new IllegalArgumentException("action can not be null");
        }
        this.action = action;
        lblAction.setText(action.getName());
        btnSchedule.setVisible(false);
        loadStudentsOfAction(action);
    }

    @FXML
    void toRegisterStudentOnAction(MouseEvent event){
        Student student = tableView.getSelectionModel().getSelectedItem();
        if(student != null){
            boolean b = registerStudentActionUseCase.includeStudentAction(action, student);
            if(b){
                Utils.showAlert("Sucesso", "Aluno registrado com sucesso na ação!", Alert.AlertType.CONFIRMATION);
            }else{
                Utils.showAlert("Erro", "Aluno não pode ser cadastrado!", Alert.AlertType.ERROR);
            }
        }else{
            Utils.showAlert("Aluno não encontrado", "É necessário escolher um aluno!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void toBack(MouseEvent event) throws IOException {
        App.setRoot("HomeProfessor");
    }

    @FXML
    void findStudent(MouseEvent event){
        String search = txtStudent.getText();
        Optional<Student> optionalStudent;
        if(getAll){
            optionalStudent = findStudentsUseCase.findStudentByEmail(search);
        }else{
            optionalStudent = findStudentsUseCase.findStudentsOfActionbyEmail(action, search);
        }
        if(optionalStudent.isPresent()){
            List<Student> students = new ArrayList<>();
            students.add(optionalStudent.get());
            tableData.clear();
            tableData.addAll(students);
        }else{
            Utils.showAlert("Aluno não encontrado", "Certifique-se de ter digitado o email correto!", Alert.AlertType.ERROR);
        }
    }
}
